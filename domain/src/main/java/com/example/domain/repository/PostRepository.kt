package com.example.domain.repository

import com.example.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getAllPosts(): Flow<List<Post>>
    fun getPostById(id: Int): Flow<Post?>
}