package com.i502tech.gitclubkotlin

import android.app.Application
import com.google.gson.Gson
import com.i502tech.gitclubkotlin.utils.RetrofitUtils
import com.safframework.log.L
import com.thejoyrun.aptpreferences.AptParser
import com.thejoyrun.aptpreferences.AptPreferencesManager



/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
class App : Application() {

    companion object {
        private var instance: Application?=null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        L.logLevel= L.LogLevel.INFO
        RetrofitUtils.init()

        AptPreferencesManager.init(this, object : AptParser {
            override fun deserialize(clazz: Class<*>, text: String): Any? {
                return Gson().fromJson(text, clazz)
            }

            override fun serialize(`object`: Any): String {
                return Gson().toJson(`object`)
            }
        })
    }
}