package com.example.kotlinmviarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlinmviarchitecture.api.RetrofitBuilder
import com.example.kotlinmviarchitecture.ui.main.state.MainViewState
import com.example.kotlinmviarchitecture.util.ApiEmptyResponse
import com.example.kotlinmviarchitecture.util.ApiErrorResponse
import com.example.kotlinmviarchitecture.util.ApiSuccessResponse

object DataRepository {

    fun getBlogPosts(): LiveData<MainViewState> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()){ response ->
                object: LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when(response) {
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    blogPosts = response.body
                                )
                            }

                            is ApiErrorResponse -> {
                                value = MainViewState() // handle error?
                            }

                            is ApiEmptyResponse -> {
                                value = MainViewState() // handle empty/error?
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<MainViewState> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)){ response ->
                object: LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when(response) {
                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    user = response.body
                                )
                            }

                            is ApiErrorResponse -> {
                                value = MainViewState() // handle error?
                            }

                            is ApiEmptyResponse -> {
                                value = MainViewState() // handle empty/error?
                            }
                        }
                    }
                }
            }
    }
}