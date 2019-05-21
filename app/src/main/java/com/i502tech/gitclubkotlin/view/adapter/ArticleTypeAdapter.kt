package com.i502tech.gitclubkotlin.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.utils.loadCircle
import com.i502tech.gitclubkotlin.utils.loadGif
import com.i502tech.gitclubkotlin.utils.loadImage
import kotlinx.android.synthetic.main.item_article_type.view.*

/**
 * description $desc$
 * created by jerry on 2019/4/25.
 */
class ArticleTypeAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article_type, null) {

    override fun convert(helper: BaseViewHolder, article: Article) {
        helper.itemView.tv_title.text = article.title
        helper.itemView.tv_name.text = article.user.userName
        helper.itemView.tv_des.text = article.des

        helper.itemView.iv_avater.loadCircle(article.user.avatar)
        val url = article.imgUrl
        if (url.endsWith("gif")){
            helper.itemView.iv_cover.loadGif(url)
        }else{
            helper.itemView.iv_cover.loadImage(url)
        }

    }
}