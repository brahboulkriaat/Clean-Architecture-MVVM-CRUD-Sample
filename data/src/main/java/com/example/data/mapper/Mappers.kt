package com.example.data.mapper

import com.example.data.local.entity.PostEntity
import com.example.data.remote.dto.PostDto
import com.example.domain.model.Post

fun PostDto.toEntity() = PostEntity(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun PostEntity.toDomain() = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)