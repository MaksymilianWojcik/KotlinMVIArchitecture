package com.example.kotlinmviarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlinmviarchitecture.api.RetrofitBuilder
import com.example.kotlinmviarchitecture.ui.main.state.MainViewState
import com.example.kotlinmviarchitecture.util.ApiEmptyResponse
import com.example.kotlinmviarchitecture.util.ApiErrorResponse
import com.example.kotlinmviarchitecture.util.ApiSuccessResponse
import com.example.kotlinmviarchitecture.util.DataState

object DataRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()){ response ->
                object: LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when(response) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    message = null,
                                    data = MainViewState(
                                        blogPosts = response.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = response.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.data(
                                    message = "HTTO 204. Returned nothing"
                                )
                            }
                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)){ response ->
                object: LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when(response) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    message = null,
                                    data = MainViewState(
                                        user = response.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = response.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.data(
                                    message = "HTTO 204. Returned nothing"
                                )
                            }
                        }
                    }
                }
            }
    }
}