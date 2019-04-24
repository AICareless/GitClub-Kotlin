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

    fun getArticleList(page: Int, size: Int): MutableLiveData<Resource<List<Article>>> {
        articleViewModel.postValue(Resource.loading(null))
        HttpUtils.ApiFunc(HttpUtils.apiService().getArticleList(page, size)) {
            articleViewModel.postValue(it)
        }
        return articleViewModel
    }
}