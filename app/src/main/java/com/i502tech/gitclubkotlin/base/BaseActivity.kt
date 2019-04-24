package com.i502tech.gitclubkotlin.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.gyf.immersionbar.ImmersionBar
import com.i502tech.gitclubkotlin.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    private lateinit var mContext: Context
    private var dialog: Dialog? = null
    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .navigationBarColor(R.color.colorPrimary)
                .init()
        initView()
        initData()
        initLisenter()
    }

    open protected fun initLisenter() {

    }

    abstract fun initData()

    open protected fun initView() {

    }

    abstract fun getLayoutId(): Int

    @SuppressLint("ShowToast")
    fun toast(string: String?){
        if (mToast == null){
            mToast = Toast.makeText(this, string, Toast.LENGTH_SHORT)
        }else{
            mToast?.setText(string)
        }
        mToast?.show()
    }

    fun toActivity(clazz: Class<*>){
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    fun toActivity(clazz: Class<*>, bundle: Bundle){
        val intent = Intent(this, clazz)
        intent.putExtras(bundle)
        startActivity(intent)
    }



}