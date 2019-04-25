package com.i502tech.gitclubkotlin.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
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
    var toolbar: Toolbar? = null
    var abTitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        initToolBar()
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题
//                .navigationBarColor(R.color.colorPrimary)
                .init()
        initView()
        initData()
        initLisenter()
    }

    abstract fun getLayoutId(): Int

    open protected fun initView() {

    }

    abstract fun initData()

    open protected fun initLisenter() {

    }

    //自己新添加的
    override fun onTitleChanged(title: CharSequence, color: Int) {
        super.onTitleChanged(title, color)
        if (abTitle != null) {
            abTitle?.text = title
        }
    }

    //自己新添加的
    private fun initToolBar() {
        toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            abTitle = toolbar?.findViewById(R.id.toolbar_title) as TextView
        }
        if (abTitle != null) {
            val actionBar = supportActionBar
            actionBar?.setDisplayShowTitleEnabled(false)
        }
    }

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