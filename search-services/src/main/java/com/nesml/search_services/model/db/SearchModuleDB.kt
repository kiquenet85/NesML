package com.nesml.search_services.model.db

import com.nesml.search_services.model.db.dao.SearchItemDAO

interface SearchModuleDB {
    fun searchItemDAO(): SearchItemDAO
}