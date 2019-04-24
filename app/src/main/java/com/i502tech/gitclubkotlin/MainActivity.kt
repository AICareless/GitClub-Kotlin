package com.i502tech.gitclubkotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.i502tech.gitclubkotlin.base.BaseActivity
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.view.adapter.ArticleAdapter
import com.i502tech.gitclubkotlin.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var mData = mutableListOf<Article>()
    val adapter = ArticleAdapter()
    private var page: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProviders.of(this).get(ArticleViewModel::class.java)
    }

    override fun initData() {
        viewModel.getArticleList(0, 10)
                .observe(this, Observer{ resource ->
                    refreshlayout.isRefreshing = false
                    if (resource?.isSuccess == true){
                        if (page == 0) {
                            adapter.setNewData(resource.data)
                        } else adapter.addData(resource.data ?: listOf())
                    } else if (resource?.isLoading == true){
                        toast("正在加载...")
                    }else toast(resource?.msg)
                })
    }

    override fun initLisenter() {
        refreshlayout.setOnRefreshListener {
            page = 0
            viewModel.getArticleList(page, 10)
        }
        adapter.setOnItemClickListener{adapter, view, position ->

        }
    }


}
