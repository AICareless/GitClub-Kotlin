package com.i502tech.gitclubkotlin.view.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.utils.GlideUtils
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
class ArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, null) {
    override fun convert(holder: BaseViewHolder, article: Article) {
        // 获取item中的TextView

        holder.itemView.tv_title?.text = article.title
        holder.itemView.tv_name_des?.text = "${article.user.nick_name}${article.des}"
        holder.itemView.tv_name_des?.text = "${article.user.nick_name}${article.des}"
        GlideUtils.displayCircle(mContext, holder.itemView.iv_avater, article.user.avatar)
        var url = article.img_url
        if (url.endsWith("gif")){
            Glide.with(mContext).asGif().load(url).into(holder.itemView.iv_cover)
        }else{
            Glide.with(mContext).load(url).into(holder.itemView.iv_cover)
        }
        holder.itemView.tv_view?.text = article.views.toString()
        holder.itemView.tv_star?.text = article.stars.toString()


    }
}