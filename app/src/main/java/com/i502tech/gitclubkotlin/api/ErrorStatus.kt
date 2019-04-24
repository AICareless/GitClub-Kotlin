package com.i502tech.gitclubkotlin.api

interface ErrorStatus {
    companion object {
        val CLIENT_PROTOCOL_ERROR = 508
        val IO_ERROR = 510
        val JSON_PARSE_ERROR = 511
        val OK = 0
        val REQUEST_FAILED = 512
        val UNSUPPORTED_ENCODING = 509
        val NETWORK_ERROR = -5
    }
}
