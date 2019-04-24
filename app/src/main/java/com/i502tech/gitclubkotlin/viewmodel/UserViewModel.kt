package com.i502tech.gitclubkotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.i502tech.gitclubkotlin.api.HttpUtils
import com.i502tech.gitclubkotlin.model.Resource
import com.i502tech.gitclubkotlin.model.bean.User

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
class UserViewModel : ViewModel() {

    val liveData: MutableLiveData<Resource<User>> = MutableLiveData()

    fun loginRegister(type: String, userName: String, password: String): MutableLiveData<Resource<User>> {
        liveData.postValue(Resource.loading(null))
        if (type == "登录"){
            HttpUtils.ApiFunc(HttpUtils.apiService().login(userName, password)) {
                liveData.postValue(it)
            }
        }else{
            HttpUtils.ApiFunc(HttpUtils.apiService().register(userName, password)) {
                liveData.postValue(it)
            }
        }
//        HttpUtils.apiService()
//                .login(userName, password)
//                .compose(RxUtils.applyBizSchedulers<Resource<User>>())
//                .subscribe({
//                    liveData.postValue(it)
//                },{
//
//                })
        return liveData
    }
}