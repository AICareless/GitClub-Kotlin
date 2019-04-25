package com.i502tech.gitclubkotlin.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.StringRes
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import java.lang.reflect.Field


/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */
object Utils {

    /**
     * dp2px
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 实现文本复制功能
     *
     * @param text
     */
    fun copyText(context: Context, text: String) {
        // 得到剪贴板管理器
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val cmb = context
                    .getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            cmb.text = text.trim { it <= ' ' }
        } else {
            val cmb = context
                    .getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            cmb.text = text.trim { it <= ' ' }
        }
    }

    /**
     * 状态栏高度
     *
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        var c: Class<*>? = null
        var obj: Any? = null
        var field: Field? = null
        var x = 0
        var statusBarHeight = 0
        try {
            c = Class.forName("com.android.internal.R\$dimen")
            obj = c!!.newInstance()
            field = c.getField("status_bar_height")
            x = Integer.parseInt(field!!.get(obj).toString())
            statusBarHeight = context.resources.getDimensionPixelSize(x)

        } catch (e1: Exception) {
            statusBarHeight = 0
            e1.printStackTrace()
        }

        return statusBarHeight
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, @StringRes id: Int) {
        showToast(context, context.getString(id))
    }


    fun setText(tv: TextView, @StringRes id: Int) {
        setText(tv, tv.context.getString(id))
    }


    fun setText(tv: TextView, text: String) {
        tv.text = text
    }

    fun showKeyboard(context: Activity) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = context.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(context)
        }
        view.requestFocus()
        imm.showSoftInput(view, 0)
    }

    fun hideKeyboard(context: Activity) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = context.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(context)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toggleSoftInput(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.toggleSoftInput(0, 0)
    }
}