package com.nesml.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nesml.commons.error.ErrorHandler
import com.nesml.commons.manager.ResourceManager
import com.nesml.commons.network.NetworkManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseCoroutineViewModel(
    resourceManager: ResourceManager,
    errorHandler: ErrorHandler,
    networkManager: NetworkManager
) : ViewModel() {
    open val errorHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            try {
                if (!networkManager.isOnline) {
                    errorState.value =
                        CommonErrorState.CriticalHandledUIError(resourceManager.getString(R.string.general_no_internet_error))
                } else {
                    errorState.value =
                        CommonErrorState.CriticalHandledUIError(resourceManager.getString(R.string.general_error_handled))
                    errorHandler.reportCriticalException(
                        exception,
                        "Handled exception on coroutine builder"
                    )
                }
            } catch (reportException: Exception) {
                val message = "Error handling exception on coroutine error handler"
                errorState.value = CommonErrorState.CriticalUIError(message)
                errorHandler.reportErrorHandlingException(exception, message)
            }
        }
    }

    private val errorState: MutableLiveData<CommonErrorState> = MutableLiveData()
    fun getErrorState(): LiveData<CommonErrorState> = errorState
}

sealed class CommonErrorState(open val message: String) {
    class CriticalHandledUIError(override val message: String) : CommonErrorState(message)
    class CriticalUIError(override val message: String) : CommonErrorState(message)
}
