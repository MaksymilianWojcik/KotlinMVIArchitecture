package com.example.kotlinmviarchitecture.ui.main.state

sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class GetUserEvent(
        val userId: String
    ): MainStateEvent()

    class NoneEvent: MainStateEvent()
}