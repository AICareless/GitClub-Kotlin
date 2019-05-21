package com.i502tech.gitclubkotlin.model.bean

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
data class Article(
        val articleId: Int,
        val category: String,
        val childCategory: Int,
        val comments: Int,
        val contributor: String,
        val contributorId: Int,
        val date: String,
        val des: String,
        val imgUrl: String,
        val link: String,
        val rank: Int,
        val reviewStatus: Int,
        val stars: Int,
        val tag: String,
        val title: String,
        val unStars: Int,
        val updateDate: String,
        val user: User,
        val views: Int,
        val wrapLink: String
)