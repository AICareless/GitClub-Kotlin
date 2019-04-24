package com.i502tech.gitclubkotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


abstract class BaseFrameLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        bindLayout(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        bindLayout(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        bindLayout(context, attrs, defStyleAttr)
    }

    protected abstract fun bindLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int)

}
