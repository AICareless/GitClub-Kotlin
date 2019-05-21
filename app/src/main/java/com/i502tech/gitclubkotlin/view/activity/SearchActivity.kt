package com.i502tech.gitclubkotlin.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.base.BaseActivity
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.utils.hideKeyboard
import com.i502tech.gitclubkotlin.utils.toast
import com.i502tech.gitclubkotlin.view.adapter.ArticleAdapter
import com.i502tech.gitclubkotlin.viewmodel.ArticleViewModel
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search_tag.view.*

/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */
class SearchActivity: BaseActivity() {

    val hotList = listOf("自定义View", "Tab", "WebView", "图片加载", "相机",
            "图表", "列表", "数据库", "蓝牙", "视频", "网络请求", "人脸识别", "OpenGL", "Canvas", "音频", "完整项目")
    private var page = 0
    var adapter = ArticleAdapter()
    private lateinit var tagAdapter: TagAdapter<String>

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProviders.of(this).get(ArticleViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun initData() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        tagAdapter = object : TagAdapter<String>(hotList) {
            override fun getView(parent: FlowLayout, position: Int, s: String): View {
                val view = layoutInflater.inflate(R.layout.item_search_tag, flowlayout, false)
                view.roundtext.text = hotList[position]
                return view
            }
        }
        flowlayout.adapter = tagAdapter
    }

    override fun initLisenter() {
        adapter.setOnLoadMoreListener({
            page++
            viewModel.query(page, 10, search_edit.text.toString())
        }, recyclerview)

        search_edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){

                hideKeyboard()
                flowlayout.visibility = View.GONE
                viewModel.query(page, 10, search_edit.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }


        flowlayout.setOnTagClickListener { view, position, parent ->
            hideKeyboard()
            flowlayout.visibility = View.GONE
            search_edit.setText(tagAdapter.getItem(position))
            page = 0
            viewModel.query(page, 10, search_edit.text.toString())
            return@setOnTagClickListener true
        }

        adapter.setOnItemClickListener { adapter, view, position ->
            val article = adapter.getItem(position) as Article
            ArticleDetailActivity.start(this, article.title, article.link)
        }

        viewModel.query(page, 10, "")
                .observe(this, Observer {resources->
                    when{
                        resources?.isSuccess == true->{
                            if (page == 0)adapter.setNewData(resources.data)
                            else adapter.addData(resources.data?: listOf())
                        }
                        resources?.isLoading == true->{

                        }
                        else-> toast(resources?.msg)
                    }
                })
    }
}