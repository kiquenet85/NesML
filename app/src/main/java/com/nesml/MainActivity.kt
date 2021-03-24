package com.nesml

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nesml.commons.BaseActivity
import com.nesml.commons.BaseFragment
import com.nesml.commons.util.ACCOUNT_MOCK
import com.nesml.search_services.repository.search.ItemSearchInfo
import com.nesml.search_services.repository.search.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var searchRepo: SearchRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NesMLApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.navigateTo(BaseFragment())
        }

        lifecycleScope.launch(errorHandler) {
            searchRepo.getAll(ItemSearchInfo(ACCOUNT_MOCK, "Motorola M6"))
                .flowOn(Dispatchers.Default)
                .collect {
                    Toast.makeText(this@MainActivity, "${it.size}", Toast.LENGTH_LONG).show()
                    Log.e("response", "size: ${it.size}")
                }
        }
    }

    val errorHandler = CoroutineExceptionHandler { coroutineScope, exception ->
        Log.e("MainActivity", "Error NesML", exception)
    }
}