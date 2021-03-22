package com.nesml.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nesml.commons.error.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseCoroutineViewModel(errorHandler: ErrorHandler) : ViewModel() {
    open val errorHandler = CoroutineExceptionHandler { coroutineScope, exception ->
        viewModelScope.launch {
            try {
                errorState.value = CommonErrorState.NonCriticalUIError
                errorHandler.reportCriticalException(
                    exception,
                    "Critical exception on coroutine error handler"
                )
            } catch (reportException: Exception) {
                errorState.value = CommonErrorState.CriticalUIError
                errorHandler.reportErrorHandlingException(
                    exception,
                    "Error handling exception on coroutine error handler",
                )
            }
        }
    }

    private val errorState: MutableLiveData<CommonErrorState> = MutableLiveData()
    fun getErrorState(): LiveData<CommonErrorState> = errorState
}

sealed class CommonErrorState {
    object NonCriticalUIError : CommonErrorState()
    object CriticalUIError : CommonErrorState()
}