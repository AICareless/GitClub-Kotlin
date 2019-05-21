package com.i502tech.gitclubkotlin.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.base.BaseActivity
import com.i502tech.gitclubkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_article_detail.*

/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */
class ArticleDetailActivity: BaseActivity() {

    companion object {
        fun start(context: Context, title: String, url: String){
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_article_detail
    }

    override fun initData() {
        title = intent.getStringExtra("title")
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.allowFileAccess = true// 设置允许访问文件数据
        webSettings.setSupportZoom(true)//支持缩放
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webView.loadUrl(intent.getStringExtra("url"))
    }

    override fun initLisenter() {
        super.initLisenter()
        webView.webViewClient = webClient
    }

    private val webClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            toast("加载中...")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            toast("加载完成...")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}