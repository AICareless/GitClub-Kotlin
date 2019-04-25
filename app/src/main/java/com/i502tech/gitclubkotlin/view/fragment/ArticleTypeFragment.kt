package com.i502tech.gitclubkotlin.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.base.BaseFragment
import com.i502tech.gitclubkotlin.view.adapter.ArticleTypeAdapter
import com.i502tech.gitclubkotlin.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article_type.*

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
class ArticleTypeFragment: BaseFragment() {

    var adapter = ArticleTypeAdapter()
    var page = 0

    private val viewModel : ArticleViewModel by lazy {
        ViewModelProviders.of(this).get(ArticleViewModel::class.java)
    }

    companion object {
        fun newInstance(type: Int): ArticleTypeFragment {
            val fragment = ArticleTypeFragment()
            val bundle = Bundle()
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_type
    }

    override fun initData() {
        val type = arguments?.getInt("type", 0)
        recyclerview.layoutManager = LinearLayoutManager(mContext)
        recyclerview.adapter = adapter
        viewModel.getMyArticleByType(type?:0, page, 10, 3)
                .observe(this, Observer { resources->
                    when{
                        resources?.isLoading == true ->{

                        }
                        resources?.isSuccess == true ->{
                            if (page == 0)adapter.setNewData(resources.data)
                            else adapter.addData(resources.data?: listOf())
                        }
                        else -> toast(resources?.msg)
                    }
                })
    }
}