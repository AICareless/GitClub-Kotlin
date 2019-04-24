package com.i502tech.gitclubkotlin.view.fragment

import android.os.Bundle
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.base.BaseFragment

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
class ArticleTypeFragment: BaseFragment() {

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

    }
}