package com.i502tech.gitclubkotlin.utils

import com.google.gson.GsonBuilder
import com.i502tech.gitclubkotlin.api.ApiService
import com.i502tech.gitclubkotlin.BuildConfig
import com.safframework.log.L
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
object RetrofitUtils {
    lateinit var apis: ApiService

    fun init(): ApiService {

        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg -> L.e(msg) })//不重写,部分手机平板需要打开日志
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        builder.addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(
                        if (BuildConfig.DEBUG) {//测试版
                            "http://www.wanandroid.com"
                        } else {//发行版
                            "http://www.wanandroid.com"
                        })
                .client(builder.build())
                .build()
        apis = retrofit.create(ApiService::class.java)
        return apis
    }


}