package com.example.kotlinmviarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.kotlinmviarchitecture.api.RetrofitBuilder
import com.example.kotlinmviarchitecture.models.Post
import com.example.kotlinmviarchitecture.models.User
import com.example.kotlinmviarchitecture.ui.main.state.MainViewState
import com.example.kotlinmviarchitecture.util.*

object DataRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<Post>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Post>>) {
                result.value = DataState.data(
                   data = MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Post>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }
        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<User, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(userId)
            }
        }.asLiveData()
    }
}