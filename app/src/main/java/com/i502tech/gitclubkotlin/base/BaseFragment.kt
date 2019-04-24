package com.i502tech.gitclubkotlin.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
abstract class BaseFragment : RxFragment() {
    var mContext: Context? = null
    private var dialog: Dialog? = null
    private var mToast: Toast? = null

    private var isViewVisibity: Boolean = false
    private var isViewCreated: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(mContext).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()

    }

    /*
  * 适合viewpager+fragment
  */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isViewVisibity = true
            lasyLoadData()
        } else {
            isViewVisibity = false
            viewInvisible()
        }
    }

    abstract fun getLayoutId(): Int

    open protected fun initView() {

    }

    abstract protected fun initData()

    open protected fun init(arguments: Bundle?) {


    }

    private fun lasyLoadData() {
        if (isViewCreated && isViewVisibity) {
            doLazy()
            isViewCreated = false
            isViewVisibity = false
        }
    }

    /*
    * 适合fragment的show和hide
    */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            hide()
        } else {
            show()
        }
    }

    protected open fun viewInvisible() {}

    protected open fun doLazy() {}

    protected open fun hide() {}

    protected open fun show() {}


}