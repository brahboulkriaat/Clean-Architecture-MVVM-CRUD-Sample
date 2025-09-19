package com.example.data.repository

import android.util.Log
import com.example.data.local.PostDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toEntity
import com.example.data.remote.PostApi
import com.example.domain.model.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao
): PostRepository {
    val posts: Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toDomain() } } //TODO("mapper")

    suspend fun getAndSavePosts() {
        try {
            val posts = api.getPosts()
            dao.insertAllPosts(posts.map { it.toEntity() }) //TODO("mapper")
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching posts", e)
        }
    }

    override fun getAllPosts(): Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toDomain() } } //TODO("mapper")

    override fun getPostById(id: Int): Flow<Post?> = dao.getPostById(id).map { it?.toDomain() } //TODO("mapper") -> posts.map { it.toDomain() } } //TODO("mapper")
}