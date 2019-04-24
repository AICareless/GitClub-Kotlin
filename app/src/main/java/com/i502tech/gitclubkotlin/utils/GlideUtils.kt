package com.i502tech.gitclubkotlin.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.i502tech.gitclubkotlin.R

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
object GlideUtils {
    fun displayCircle(context: Context, imageView: ImageView, url:String){
        Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                //默认淡入淡出动画
                .transition(withCrossFade())
                //缓存策略,跳过内存缓存【此处应该设置为false，否则列表刷新时会闪一下】
                .skipMemoryCache(false)
                //缓存策略,硬盘缓存-仅仅缓存最终的图像，即降低分辨率后的（或者是转换后的）
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //设置图片加载的优先级
                .priority(Priority.HIGH)
                //圆形
                .circleCrop()
                .into(imageView)
    }
}