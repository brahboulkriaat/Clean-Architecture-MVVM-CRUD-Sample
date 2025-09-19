package com.example.app.data.remote

import com.example.app.domain.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}