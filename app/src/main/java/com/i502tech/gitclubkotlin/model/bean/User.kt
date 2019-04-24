package com.i502tech.gitclubkotlin.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
@Parcelize
data class User(
        var admin_status: Int,
        var avatar: String,
        var city: String,
        var date: String,
        var gender: String,
        var nick_name: String,
        var open_id: String,
        var score: Int,
        var session_key: String,
        var user_id: Int
):Parcelable