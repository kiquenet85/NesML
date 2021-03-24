package com.nesml.search_services.di.module

import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.network.NetworkManager
import com.nesml.search_services.api.SearchAPI
import com.nesml.search_services.repository.search.SearchRepository
import com.nesml.search_services.repository.search.sources.SearchLocalSource
import com.nesml.search_services.repository.search.sources.SearchLocalSourceImp
import com.nesml.search_services.repository.search.sources.SearchRemoteSource
import com.nesml.search_services.repository.search.sources.SearchRemoteSourceImp
import com.nesml.search_services.repository.search.sources.attribute.AttributeLocalSource
import com.nesml.search_services.repository.search.sources.attribute.AttributeLocalSourceImp
import com.nesml.search_services.repository.search.sources.attribute_value.AttributeValueLocalSource
import com.nesml.search_services.repository.search.sources.attribute_value.AttributeValueLocalSourceImp
import com.nesml.search_services.repository.search.sources.installment.InstallmentLocalSource
import com.nesml.search_services.repository.search.sources.installment.InstallmentLocalSourceImp
import dagger.Module
import dagger.Provides

@Module
class SearchServicesModule {

    @Provides
    fun provideSearchRepository(
        searchLocalSource: SearchLocalSource,
        localSourceInstallment: InstallmentLocalSource,
        localSourceAttribute: AttributeLocalSource,
        localSourceAttributeValue: AttributeValueLocalSource,
        searchRemoteSource: SearchRemoteSource,
        errorHandler: ErrorHandler
    ): SearchRepository =
        SearchRepository(
            searchLocalSource,
            localSourceInstallment,
            localSourceAttribute,
            localSourceAttributeValue,
            searchRemoteSource,
            errorHandler
        )

    @Provides
    fun provideInstallmentLocalSource(installmentLocalSourceImp: InstallmentLocalSourceImp): InstallmentLocalSource =
        installmentLocalSourceImp

    @Provides
    fun provideAttributeLocalSource(attributeLocalSourceImp: AttributeLocalSourceImp): AttributeLocalSource =
        attributeLocalSourceImp

    @Provides
    fun provideAttributeValueLocalSource(attributeValueLocalSourceImp: AttributeValueLocalSourceImp): AttributeValueLocalSource =
        attributeValueLocalSourceImp

    @Provides
    fun provideSearchLocalSource(searchLocalSourceImp: SearchLocalSourceImp): SearchLocalSource =
        searchLocalSourceImp

    @Provides
    fun provideSearchRemoteSource(searchRemoteSourceImp: SearchRemoteSourceImp): SearchRemoteSource =
        searchRemoteSourceImp

    @Provides
    fun provideSearchAPI(networkManager: NetworkManager): SearchAPI =
        networkManager.defaultRetrofit.create(SearchAPI::class.java)
}