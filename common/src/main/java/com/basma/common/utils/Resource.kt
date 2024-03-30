package com.basma.common.utils

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
}