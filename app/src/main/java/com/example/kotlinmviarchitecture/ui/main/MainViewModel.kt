package com.example.kotlinmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kotlinmviarchitecture.models.Post
import com.example.kotlinmviarchitecture.models.User
import com.example.kotlinmviarchitecture.repository.DataRepository
import com.example.kotlinmviarchitecture.ui.main.state.MainStateEvent
import com.example.kotlinmviarchitecture.ui.main.state.MainViewState
import com.example.kotlinmviarchitecture.util.AbsentLiveData

class MainViewModel : ViewModel(){

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent){ stateEvent -> // listetning for _stateEvent, if it changes than switchmap will detect that and execute this code:

            stateEvent?.let {
                handleStateEvent(it)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState>{
        when(stateEvent){
            is MainStateEvent.GetBlogPostsEvent -> {
                return DataRepository.getBlogPosts()
            }

            is MainStateEvent.GetUserEvent -> {
                return DataRepository.getUser(stateEvent.userId)

            }

            is MainStateEvent.NoneEvent -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value?.let {
            it // set it to value
        }?: MainViewState() // no value so create new one
        return value;
    }

    fun setBlogListData(blogPosts: List<Post>){
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun  setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun setStateEvent(event: MainStateEvent){
        _stateEvent.value = event
    }

}