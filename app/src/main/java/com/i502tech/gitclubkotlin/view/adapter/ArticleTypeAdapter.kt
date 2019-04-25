package com.i502tech.gitclubkotlin.view.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.utils.GlideUtils
import kotlinx.android.synthetic.main.item_article_type.view.*

/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */
class ArticleTypeAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article_type, null) {

    override fun convert(helper: BaseViewHolder, article: Article) {
        helper.itemView.tv_title.text = article.title
        helper.itemView.tv_name.text = article.user.nick_name
        helper.itemView.tv_des.text = article.des

        GlideUtils.displayCircle(mContext, helper.itemView.iv_avater, article.user.avatar)
        val url = article.img_url
        if (url.endsWith("gif")){
            Glide.with(mContext).asGif().load(url).into(helper.itemView.iv_cover)
        }else{
            Glide.with(mContext).load(url).into(helper.itemView.iv_cover)
        }

    }
}