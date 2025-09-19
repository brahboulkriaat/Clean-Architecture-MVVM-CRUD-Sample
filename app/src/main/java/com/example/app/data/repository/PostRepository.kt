package com.example.app.data.repository

import android.util.Log
import com.example.app.data.local.PostDao
import com.example.app.data.remote.PostApi
import com.example.app.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao
) {
    val posts: Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toPost() } }

    suspend fun getAndSavePosts() {
        try {
            val posts = api.getPosts()
            dao.insertAllPosts(posts.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching posts", e)
        }
    }
}