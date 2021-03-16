package com.nesml.commons.repository.base.operation

sealed class OperationItem<T> {
    class ValidItem<T>(val value: T) : OperationItem<T>()
    class ItemException<T>(val throwable: Throwable) : OperationItem<T>()
}
