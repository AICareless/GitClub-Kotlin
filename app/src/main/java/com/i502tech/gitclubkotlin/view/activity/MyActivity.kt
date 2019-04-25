package com.i502tech.gitclubkotlin.view.activity

import android.content.Intent
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.app.SettingsPreferences
import com.i502tech.gitclubkotlin.base.BaseActivity
import com.i502tech.gitclubkotlin.model.bean.User
import com.i502tech.gitclubkotlin.view.adapter.FragmentAdapter
import com.i502tech.gitclubkotlin.view.fragment.ArticleTypeFragment
import kotlinx.android.synthetic.main.activity_my.*

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
class MyActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_my
    }

    override fun initData() {
        var fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(0), "收藏")
        fragmentAdapter.addFragment(ArticleTypeFragment.newInstance(1), "贡献")
        viewPager.adapter = fragmentAdapter
        tabLayout.setViewPager(viewPager)
        viewPager.currentItem = 0
    }

    override fun initLisenter() {
        btn_logout.setOnClickListener{
            SettingsPreferences.get().user = User()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }


}