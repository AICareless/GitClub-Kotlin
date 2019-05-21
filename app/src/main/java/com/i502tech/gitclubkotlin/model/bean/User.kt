package com.i502tech.gitclubkotlin.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
@Parcelize
data class User(
        var adminStatus: Int,
        var avatar: String,
        var city: String,
        var date: String,
        var gender: String,
        var userName: String,
        var openId: String,
        var score: Int,
        var sessionKey: String,
        var userId: Int
):Parcelable {
    constructor() : this(0, "","","","","","",0,"",0)
}