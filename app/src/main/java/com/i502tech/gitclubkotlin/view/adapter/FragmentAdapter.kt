package com.i502tech.gitclubkotlin.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

import java.util.ArrayList

/**
 * 作者：刘磊 on 2016/10/21 16:41
 * 公司：希顿科技
 */

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mFragments = ArrayList<Fragment>()
    private val mFragmentTitles = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        mFragments.add(fragment)
        mFragmentTitles.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitles[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //            super.destroyItem(container, position, object);
    }
}
