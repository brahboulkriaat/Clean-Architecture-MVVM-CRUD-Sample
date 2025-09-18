package com.example.sample.data.remote

import com.example.sample.domain.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}