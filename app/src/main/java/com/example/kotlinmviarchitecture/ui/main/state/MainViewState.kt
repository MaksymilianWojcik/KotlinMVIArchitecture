package com.example.kotlinmviarchitecture.ui.main.state

import com.example.kotlinmviarchitecture.models.Post
import com.example.kotlinmviarchitecture.models.User


data class MainViewState(
    var blogPosts: List<Post>? = null,
    var user: User? = null
)