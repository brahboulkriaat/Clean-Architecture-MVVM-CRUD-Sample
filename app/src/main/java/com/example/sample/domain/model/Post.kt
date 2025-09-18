package com.example.sample.domain.model

import com.example.sample.data.local.PostEntity

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun toEntity() = PostEntity(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}