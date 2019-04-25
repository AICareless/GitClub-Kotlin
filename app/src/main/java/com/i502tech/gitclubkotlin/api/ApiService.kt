package com.i502tech.gitclubkotlin.api

import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.model.bean.User
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
interface ApiService {

    @POST("login")
    fun login(@Query("userName")userName:String, @Query("password")password:String):Observable<BaseResponse<User>>


    @POST("register")
    fun register(@Query("username")userName:String, @Query("password")password:String):Observable<BaseResponse<User>>

    //获取文章列表
    @POST("getArticleList")
    fun getArticleList(@Query("page")page:Int, @Query("size")size:Int): Observable<BaseResponse<List<Article>>>

    //获取开源库的总收录数
    @POST("getArticleTotals")
    fun getArticleTotals(): Observable<BaseResponse<Int>>

    //关键字查询
    @POST("query")
    fun query(@Query("page")page:Int, @Query("size")size:Int, @Query("query")query: String): Observable<BaseResponse<List<Article>>>

    //我的点赞(收藏)文章列表
    @POST("getMyStarArticles")
    fun getMyStarArticles(@Query("page")page:Int, @Query("size")size:Int, @Query("user_id")user_id: Int): Observable<BaseResponse<List<Article>>>

    //获取我贡献的文章列表
    @POST("getMyContributeArticles")
    fun getMyContributeArticles(@Query("page")page:Int, @Query("size")size:Int, @Query("user_id")user_id: Int): Observable<BaseResponse<List<Article>>>



}