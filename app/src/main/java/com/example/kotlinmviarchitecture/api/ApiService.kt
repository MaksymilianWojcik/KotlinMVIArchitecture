package com.example.kotlinmviarchitecture.api

import androidx.lifecycle.LiveData
import com.example.kotlinmviarchitecture.models.Post
import com.example.kotlinmviarchitecture.models.User
import com.example.kotlinmviarchitecture.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

    @GET("/placeholder/blogs}")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<Post>>>
}