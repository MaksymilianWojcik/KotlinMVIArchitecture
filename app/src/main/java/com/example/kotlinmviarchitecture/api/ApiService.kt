package com.example.kotlinmviarchitecture.api

import com.example.kotlinmviarchitecture.models.Post
import com.example.kotlinmviarchitecture.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): User

    @GET("/placeholder/blogs}")
    fun getBlogPosts(): List<Post>
}