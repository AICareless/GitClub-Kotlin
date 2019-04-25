package com.i502tech.gitclubkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.i502tech.gitclubkotlin.api.HttpUtils
import com.i502tech.gitclubkotlin.model.Resource
import com.i502tech.gitclubkotlin.model.bean.Article

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
class ArticleViewModel: ViewModel() {
    val articleViewModel: MutableLiveData<Resource<List<Article>>> = MutableLiveData()
    val sumViewModel: MutableLiveData<Resource<Int>> = MutableLiveData()

    fun getArticleList(page: Int, size: Int): MutableLiveData<Resource<List<Article>>> {
        articleViewModel.postValue(Resource.loading(null))
        HttpUtils.create(HttpUtils.apiService().getArticleList(page, size)) {
            articleViewModel.postValue(it)
        }
        return articleViewModel
    }

    fun getArticleTotals(): MutableLiveData<Resource<Int>>{
        HttpUtils.create(HttpUtils.apiService().getArticleTotals()){
            sumViewModel.postValue(it)
        }
        return sumViewModel
    }

    fun query(page: Int, size: Int, query: String): MutableLiveData<Resource<List<Article>>>{
        articleViewModel.postValue(Resource.loading(null))
        HttpUtils.create(HttpUtils.apiService().query(page, size, query)){
            articleViewModel.postValue(it)
        }
        return articleViewModel
    }

    //获取我的点赞/贡献
    fun getMyArticleByType(type: Int, page: Int, size: Int, user_id: Int): MutableLiveData<Resource<List<Article>>>{
        articleViewModel.postValue(Resource.loading(null))
        if (type == 0){//点赞收藏
            HttpUtils.create(HttpUtils.apiService().getMyStarArticles(page, size, user_id)){
                articleViewModel.postValue(it)
            }
        }else{
            HttpUtils.create(HttpUtils.apiService().getMyContributeArticles(page, size, user_id)){
                articleViewModel.postValue(it)
            }
        }

        return articleViewModel
    }


}