package com.bakhmetow.data.repository

import com.bakhmetow.domain.model.Error
import com.bakhmetow.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline mapError: (Exception) -> Error,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
): Flow<Resource<ResultType>> =
    flow {
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            emit(Resource.Loading(data))
            try {
                val result = fetch()
                saveFetchResult(result)
                query().map<ResultType, Resource<ResultType>> { Resource.Success(it) }
            } catch (ex: Exception) {
                query().map<ResultType, Resource<ResultType>> { Resource.Failure(mapError(ex), it) }
            }
        } else {
            query().map<ResultType, Resource<ResultType>> { Resource.Success(it) }
        }

        emitAll(flow)
    }