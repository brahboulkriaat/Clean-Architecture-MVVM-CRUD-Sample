package com.example.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val dao: PostDao
) {
    val posts: Flow<List<Post>> = dao.getAllPosts().map { posts -> posts.map { it.toPost() } }

    suspend fun getAndSavePosts() {
        val posts = api.getPosts()
        dao.insertAllPosts(posts.map { it.toEntity() })
    }
}