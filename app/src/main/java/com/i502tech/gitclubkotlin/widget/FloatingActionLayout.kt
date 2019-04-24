package com.i502tech.gitclubkotlin.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.view.MyActivity
import kotlinx.android.synthetic.main.layout_fb.view.*


/**
 * description $desc$
 * created by jerry on 2019/4/12.
 */
internal class FloatingActionLayout : BaseFrameLayout {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun bindLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.layout_fb, this, true)
        this.mContext = context
        initView()
        iniEvent()
    }

    private fun iniEvent() {
        fb_person.setOnClickListener {
            mContext?.startActivity(Intent(mContext, MyActivity::class.java))
        }
        fb_submission.setOnClickListener {

        }
    }

    private fun initView() {
        fb_menu?.setClosedOnTouchOutside(true)
        fb_menu?.setMenuButtonShowAnimation(AnimationUtils.loadAnimation(context, R.anim.fab_in))
        fb_menu?.setMenuButtonHideAnimation(AnimationUtils.loadAnimation(context, R.anim.fab_out))
    }


    fun show() {
        fb_menu?.showMenu(true)
    }

    fun hide() {
        fb_menu?.hideMenu(true)
    }
}
