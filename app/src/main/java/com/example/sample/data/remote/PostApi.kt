package com.example.sample.data.remote

import com.example.sample.domain.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}