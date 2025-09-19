package com.example.data.repository

import android.util.Log
import com.example.data.local.PostDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.model.Post
import com.example.data.remote.PostApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao
) {
    val posts: Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toDomain() } } //TODO("mapper")

    suspend fun getAndSavePosts() {
        try {
            val posts = api.getPosts()
            dao.insertAllPosts(posts.map { it.toEntity() }) //TODO("mapper")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching posts", e)
        }
    }
}