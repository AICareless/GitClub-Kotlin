package com.i502tech.gitclubkotlin.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.i502tech.gitclubkotlin.R
import com.i502tech.gitclubkotlin.model.bean.Article
import com.i502tech.gitclubkotlin.utils.loadCircle
import com.i502tech.gitclubkotlin.utils.loadGif
import com.i502tech.gitclubkotlin.utils.loadImage
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
class ArticleAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, null) {
    override fun convert(holder: BaseViewHolder, article: Article) {
        // 获取item中的TextView

        holder.itemView.tv_title?.text = article.title
        holder.itemView.tv_name_des?.text = article.user.userName + article.des
        holder.itemView.iv_avater.loadCircle(article.user.avatar)
        val url = article.imgUrl
        if (url.endsWith("gif")){
            holder.itemView.iv_cover.loadGif(url)
        }else{
            holder.itemView.iv_cover.loadImage(url)
        }
        holder.itemView.tv_view?.text = article.views.toString()
        holder.itemView.tv_star?.text = article.stars.toString()


    }
}