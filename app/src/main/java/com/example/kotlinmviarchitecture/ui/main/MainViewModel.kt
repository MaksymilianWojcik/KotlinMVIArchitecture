package com.example.kotlinmviarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
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
                return AbsentLiveData.create()
            }

            is MainStateEvent.GetUserEvent -> {
                return AbsentLiveData.create()

            }

            is MainStateEvent.NoneEvent -> {
                return AbsentLiveData.create()
            }
        }
    }

}