package com.i502tech.gitclubkotlin.api

/**
 * Created by zhanggonglin on 2018/8/3.
 */

class ResultException : Throwable {

    var status: Int = 0
    var msg: String? = null

    constructor() : super() {
        // TODO Auto-generated constructor stub
    }

    constructor(detailMessage: String, throwable: Throwable) : super(detailMessage, throwable) {
        // TODO Auto-generated constructor stub
    }

    constructor(status: Int, msg: String) {
        this.status = status
        this.msg = msg
    }
}
