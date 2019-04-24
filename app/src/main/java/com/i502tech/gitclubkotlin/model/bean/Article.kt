package com.i502tech.gitclubkotlin.model.bean

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
data class Article(
        val article_id: Int,
        val category: String,
        val child_category: Int,
        val comments: Int,
        val contributor: String,
        val contributor_id: Int,
        val date: String,
        val des: String,
        val img_url: String,
        val link: String,
        val rank: Int,
        val review_status: Int,
        val stars: Int,
        val tag: String,
        val title: String,
        val un_stars: Int,
        val update_date: String,
        val user: User,
        val views: Int,
        val wrap_link: String
)