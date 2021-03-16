@file:Suppress("UNCHECKED_CAST")

package com.nesml.commons.repository.base.operation

import com.nesml.commons.repository.base.RepositoryPolicy
import com.nesml.commons.repository.base.error.RemoteNetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.yield

interface RepositoryReadOperation<Remote, Local, Info, Return> : RepositoryPolicy<Info> {

    suspend fun execute(info: Info): Flow<Return> {
        val emmitRemoteErrors = flow<OperationItem<Return>> {
            if (shouldGoRemote(info)) {
                yield()
                val remoteResult = endpoint(info)
                val transformedResult = transformRemoteResult(remoteResult, info)
                updateDatabase(transformedResult, info)
            }
        }.catch { e ->
            emit(
                OperationItem.ItemException(
                    RemoteNetworkException(
                        e
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
        return listOf(readFromDatabase(info), emmitRemoteErrors).merge() as Flow<Return>
    }

    suspend fun endpoint(info: Info): Remote = info as Remote
    suspend fun transformRemoteResult(remoteData: Remote, info: Info): Local = remoteData as Local
    suspend fun updateDatabase(data: Local, info: Info): Boolean = false
    suspend fun readFromDatabase(info: Info): Flow<Return>
}
