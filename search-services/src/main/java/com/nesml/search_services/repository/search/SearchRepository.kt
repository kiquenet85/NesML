package com.nesml.search_services.repository.search

import com.nesml.commons.repository.base.RefreshRateLimit
import com.nesml.commons.repository.base.RefreshRateLimit.Companion.NO_DIFF_TIME
import com.nesml.commons.repository.base.operation.RepositoryReadOperation
import com.nesml.search_services.model.db.entity.SearchItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class SearchRepository(
    private val localSource: SearchLocalSource
) : RefreshRateLimit {
    private var timeLastRemoteOperations = NO_DIFF_TIME

    @ExperimentalCoroutinesApi
    suspend fun getAll(info: ItemSearchInfo): Flow<List<SearchItem>> {
        val operation = object :
            RepositoryReadOperation<List<String>, List<SearchItem>, ItemSearchInfo, List<SearchItem>> {

            override suspend fun shouldGoRemote(info: ItemSearchInfo): Boolean {
                return false
                /*if (info.requiresRemote) {
                    true
                } else {
                    //shouldRefreshData(timeLastRemoteOperations, info.timeOperation)
                    false
                }*/
            }

            override suspend fun endpoint(info: ItemSearchInfo): List<String> {
                return emptyList() //remoteSource.getAll(info.accountId)
            }

            override suspend fun transformRemoteResult(
                remoteData: List<String>,
                info: ItemSearchInfo
            ): List<SearchItem> {
                return remoteData.map { SearchItem(it) }
            }

            override suspend fun updateDatabase(
                data: List<SearchItem>,
                info: ItemSearchInfo
            ): Boolean {
                return localSource.createOrUpdate(info.accountId, data)
            }

            override suspend fun readFromDatabase(info: ItemSearchInfo): Flow<List<SearchItem>> {
                return localSource.getAll(info.accountId)
            }
        }
        return operation.execute(info)
    }
}

data class ItemSearchInfo(
    val accountId: String,
    val requiresRemote: Boolean = false,
    val timeOperation: Long = System.currentTimeMillis()
)