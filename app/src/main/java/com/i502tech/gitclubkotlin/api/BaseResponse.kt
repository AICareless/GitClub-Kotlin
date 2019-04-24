package com.i502tech.gitclubkotlin.api

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
data class BaseResponse<out T> (val code: Int, val msg: String, val data: T) : ErrorStatus