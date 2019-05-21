package com.i502tech.gitclubkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.i502tech.gitclubkotlin.R
import kotlinx.android.synthetic.main.item_article.view.*
import java.lang.reflect.Field

/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */

/**
 * dp2px
 */
fun Context.dp2px(dpValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 实现文本复制功能
 *
 * @param text
 */
fun Context.copyText(text: String) {
    // 得到剪贴板管理器
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
        val cmb = this
                .getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
        cmb.text = text.trim { it <= ' ' }
    } else {
        val cmb = this
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
fun Context.getStatusBarHeight(): Int {
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
        statusBarHeight = this.resources.getDimensionPixelSize(x)

    } catch (e1: Exception) {
        statusBarHeight = 0
        e1.printStackTrace()
    }

    return statusBarHeight
}

fun Activity.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    view.requestFocus()
    imm.showSoftInput(view, 0)
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.toActivity(clazz: Class<*>){
    val intent = Intent(this, clazz)
    startActivity(intent)
}

fun Activity.toActivity(clazz: Class<*>, bundle: Bundle){
    val intent = Intent(this, clazz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
            .load(url)
            .error(R.mipmap.ic_launcher_round)
            //默认淡入淡出动画
            .transition(DrawableTransitionOptions.withCrossFade())
            //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
            .skipMemoryCache(false)
            //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //设置图片加载的优先级
            .priority(Priority.HIGH)
            .into(this)
}

fun ImageView.loadCircle(url: String) {
    Glide.with(context)
            .load(url)
            .error(R.mipmap.ic_launcher_round)
            //默认淡入淡出动画
            .transition(DrawableTransitionOptions.withCrossFade())
            //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
            .skipMemoryCache(false)
            //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //设置图片加载的优先级
            .priority(Priority.HIGH)
            //圆形
            .circleCrop()
            .into(this)
}

fun ImageView.loadGif(url: String){
    Glide.with(context)
            .asGif()
            .load(url)
            .error(R.mipmap.ic_launcher_round)
            //默认淡入淡出动画
            .transition(DrawableTransitionOptions.withCrossFade())
            //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
            .skipMemoryCache(false)
            //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //设置图片加载的优先级
            .priority(Priority.HIGH)
            .into(this)
}