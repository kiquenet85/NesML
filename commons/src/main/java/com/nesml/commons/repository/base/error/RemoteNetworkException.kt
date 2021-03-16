package com.nesml.commons.repository.base.error

class RemoteNetworkException(originalException: Throwable) : Exception(originalException.message)