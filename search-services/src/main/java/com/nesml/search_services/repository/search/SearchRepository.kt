package com.nesml.search_services.repository.search

import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.repository.base.RefreshRateLimit
import com.nesml.commons.repository.base.operation.RepositoryReadOperation
import com.nesml.commons.util.Optional
import com.nesml.search_services.model.network.SearchItemDTO
import com.nesml.search_services.repository.search.sources.SearchLocalSource
import com.nesml.search_services.repository.search.sources.SearchRemoteSource
import com.nesml.search_services.repository.search.sources.attribute.AttributeLocalSource
import com.nesml.search_services.repository.search.sources.attribute_value.AttributeValueLocalSource
import com.nesml.search_services.repository.search.sources.installment.InstallmentLocalSource
import com.nesml.storage.model.search.entity.Attribute
import com.nesml.storage.model.search.entity.AttributeValue
import com.nesml.storage.model.search.entity.Installment
import com.nesml.storage.model.search.entity.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val localSource: SearchLocalSource,
    private val localSourceInstallment: InstallmentLocalSource,
    private val localSourceAttribute: AttributeLocalSource,
    private val localSourceAttributeValue: AttributeValueLocalSource,
    private val remoteSource: SearchRemoteSource,
    private val errorHandler: ErrorHandler
) : RefreshRateLimit {

    suspend fun getAll(info: ItemSearchInfo): Flow<List<SearchItem>> {
        val operation = object :
            RepositoryReadOperation<List<SearchItemDTO>, List<SearchItem>, ItemSearchInfo, List<SearchItem>> {

            override suspend fun shouldGoRemote(info: ItemSearchInfo): Boolean {
                return true
            }

            override suspend fun endpoint(info: ItemSearchInfo): List<SearchItemDTO> {
                return remoteSource.getAll(info.query).searchItems
            }

            override suspend fun transformRemoteResult(
                remoteData: List<SearchItemDTO>,
                info: ItemSearchInfo
            ): List<SearchItem> {
                return remoteData.map {
                    SearchItem(
                        it.id,
                        info.accountId,
                        it.site_id,
                        it.title,
                        it.price,
                        it.sale_price,
                        it.currency_id,
                        it.available_quantity,
                        it.sold_quantity,
                        it.buying_mode,
                        it.listing_type_id,
                        it.stop_time,
                        it.condition,
                        it.permalink,
                        it.thumbnail,
                        it.thumbnail_id,
                        it.accepts_mercadopago,
                        it.original_price,
                        it.category_id,
                        it.official_store_id,
                        it.domain_id,
                        it.catalog_product_id
                    ).apply {
                        installment = Installment(
                            id = UUID.randomUUID().toString(),
                            searchItemId = it.id,
                            quantity = it.installmentDTO?.quantity,
                            amount = it.installmentDTO?.amount,
                            rate = it.installmentDTO?.rate,
                            currency_id = it.installmentDTO?.currency_id
                        )
                        attributes = it.attributes?.map { attributeDTO ->
                            val attributeId = UUID.randomUUID().toString()
                            Attribute(
                                id = attributeId,
                                searchItemId = it.id,
                                name = attributeDTO.name,
                                value_name = attributeDTO.value_name,
                            ).apply {
                                values = attributeDTO.values?.map { attributeValueDTO ->
                                    AttributeValue(
                                        id = UUID.randomUUID().toString(),
                                        attributeId = attributeId,
                                        searchItemId = it.id,
                                        name = attributeValueDTO.name
                                    )
                                }
                            }
                        }
                    }
                }
            }

            override suspend fun updateDatabase(
                data: List<SearchItem>,
                info: ItemSearchInfo
            ): Boolean {

                localSource.createOrUpdate(info.accountId, data)
                val listInstallment = mutableListOf<Installment>()
                val listAttribute = mutableListOf<Attribute>()
                val listAttributeValues = mutableListOf<AttributeValue>()
                data.forEach { searchItem ->
                    searchItem.installment?.let {
                        listInstallment.add(it)
                    }
                    searchItem.attributes?.let { attributes ->
                        listAttribute.addAll(attributes)
                        attributes.forEach { attribute ->
                            attribute.values?.let {
                                listAttributeValues.addAll(it)
                            }
                        }
                    }
                }
                localSourceInstallment.createOrUpdate(listInstallment)
                localSourceAttribute.createOrUpdate(listAttribute)
                localSourceAttributeValue.createOrUpdate(listAttributeValues)

                return true
            }

            override suspend fun readFromDatabase(info: ItemSearchInfo): Flow<List<SearchItem>> {
                return localSource.getAll(info.accountId)
                    .combine(localSourceInstallment.getAll()) { searchItems, installments ->
                        val mapInstallment = installments.map { it.searchItemId to it }.toMap()
                        searchItems.forEach {
                            it.installment = mapInstallment[it.id]
                        }
                        searchItems
                    }.combine(localSourceAttribute.getAll()
                        .combine(localSourceAttributeValue.getAll()) { attributes, values ->
                            val valuesMap = values.groupBy { it.attributeId }
                            attributes.forEach {
                                it.values = valuesMap[it.id]
                            }
                            attributes
                        }) { searchItems, attributes ->
                        val attributesMap = attributes.groupBy { it.searchItemId }
                        searchItems.forEach {
                            it.attributes = attributesMap[it.id]
                        }
                        searchItems
                    }
            }

            override fun getErrorHandler() = errorHandler
        }
        return operation.execute(info)
    }

    suspend fun getById(searchItemId: String): Flow<SearchItem> {
        return (localSource.getById(searchItemId)).map {
            if (it is Optional.None) {
                throw IllegalStateException("Clicked item does not exist on the database")
            } else {
                (it as Optional.Some).element
            }
        }
            .combine(localSourceInstallment.getBySearchItemId(searchItemId)) { searchItem, installment ->
                if (installment is Optional.Some) searchItem.installment = installment.element
                searchItem
            }.combine(localSourceAttribute.getById(searchItemId)) { searchItem, attributes ->
                searchItem.attributes = attributes
                searchItem
            }
            .combine(localSourceAttributeValue.getById(searchItemId)) { searchItem, attributeValues ->
                val mapValuesByAttribute = attributeValues.groupBy { it.attributeId }
                searchItem.attributes?.forEach {
                    //TODO you can group by on the SQL directly
                    it.values = mapValuesByAttribute[it.id]
                }
                searchItem
            }
    }
}

data class ItemSearchInfo(
    val accountId: String,
    val query: String,
    val requiresRemote: Boolean = true,
    val timeOperation: Long = System.currentTimeMillis()
)