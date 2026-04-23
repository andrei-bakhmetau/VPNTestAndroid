package com.bakhmetow.data.repository

import com.bakhmetow.data.local.CountryDao
import com.bakhmetow.data.mapper.CountryDtoToEntityMapper
import com.bakhmetow.data.mapper.CountryEntityToDomainMapper
import com.bakhmetow.data.mapper.ExceptionToDomainErrorMapper
import com.bakhmetow.data.remote.CountryApi
import com.bakhmetow.domain.model.Country
import com.bakhmetow.domain.model.Resource
import com.bakhmetow.domain.repository.CountryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CountryRepositoryImpl @Inject constructor(
    @param:Named("IODispatcher") private val dispatcher: CoroutineDispatcher,
    private val localDataSource: CountryDao,
    @param:Named("MockApi") private val countryApi: CountryApi,
    private val countryDtoToEntityMapper: CountryDtoToEntityMapper,
    private val countryEntityToDomainMapper: CountryEntityToDomainMapper,
    private val exceptionToDomainErrorMapper: ExceptionToDomainErrorMapper,
) : CountryRepository {
    override fun getCountries(): Flow<Resource<List<Country>>> =
        networkBoundResource(
            query = {
                localDataSource.getAll()
                    .map { entities -> countryEntityToDomainMapper(entities) }
            },
            fetch = {
                countryApi
                    .getCountries()
                    .map { countryDto -> countryDtoToEntityMapper(countryDto) }
                    .toTypedArray()
            },
            saveFetchResult = { countries -> localDataSource.insert(*countries) },
            mapError = { ex -> exceptionToDomainErrorMapper.map(ex) },
        ).flowOn(dispatcher)

    override suspend fun refreshCountries(): Resource<Unit> =
        withContext(dispatcher) {
            try {
                val countries = countryApi
                    .getCountries()
                    .map { countryDto -> countryDtoToEntityMapper(countryDto) }
                    .toTypedArray()
                localDataSource.insert(*countries)
                Resource.Success(Unit)
            } catch (ex: Exception) {
                Resource.Failure(exceptionToDomainErrorMapper.map(ex))
            }
        }
}