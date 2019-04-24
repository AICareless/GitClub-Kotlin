package com.i502tech.gitclubkotlin.model

/**
 * description $desc$
 * created by jerry on 2019/4/18.
 */
class Resource<T> private constructor(val status: Status, val data: T?,
                                      val msg: String) {

    val isSuccess: Boolean
        get() = status == Status.SUCCESS

    val isLoading: Boolean
        get() = status == Status.LOADING

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "OK")
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, "loading")
        }
    }
}
