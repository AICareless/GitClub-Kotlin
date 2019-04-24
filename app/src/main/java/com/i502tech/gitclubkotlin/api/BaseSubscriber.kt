package com.i502tech.gitclubkotlin.api

import android.util.Log

import com.i502tech.gitclubkotlin.model.Resource

import java.net.ConnectException
import java.net.SocketTimeoutException

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * 实体类的请求
 *
 * @param <T>
</T> */
class BaseSubscriber<T>(private val mListener: (Resource<T>) -> Unit) : Observer<BaseResponse<T>> {
    //用于取消连接
    private var mDisposeable: Disposable? = null

    override fun onError(e: Throwable) {
        var msg = ""
        if (e is SocketTimeoutException) {
            msg = "请检查您的网络设置"
        } else if (e is ConnectException || e is HttpException) {
            msg = "请检查您的网络设置"
        } else if (e is ResultException) {
            msg = e.toString()
        }
        mListener.invoke(Resource.error(msg))
    }

    override fun onComplete() {
        Log.i("BaseSubscriber", "onCompleted: 获取数据完成！")
    }

    override fun onSubscribe(d: Disposable) {
        //拿到Disposable对象可随时取消请求
        mDisposeable = d
    }

    override fun onNext(response: BaseResponse<T>) {
        if (response.code == ErrorStatus.OK) {
            mListener.invoke(Resource.success(response.data))
        } else {
            mListener.invoke(Resource.error(response.msg))
        }
    }

}
