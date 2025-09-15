package com.example.sample

import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}