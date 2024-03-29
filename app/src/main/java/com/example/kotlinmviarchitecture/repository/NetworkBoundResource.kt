package com.example.kotlinmviarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.kotlinmviarchitecture.util.*
import com.example.kotlinmviarchitecture.util.Constants.Companion.TESTING_NETWORK_DELAY
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

abstract class NetworkBoundResource<ResponseObject, ViewStateType>{

    protected val result = MediatorLiveData<DataState<ViewStateType>>() // just accessible in repository, so can be protected

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(Dispatchers.IO){
            delay(TESTING_NETWORK_DELAY)

            withContext(Main){
                val apiResponse = createCall()
                result.addSource(apiResponse) {response ->
                    result.removeSource(apiResponse)

                    handleNetworkCall(response)
                }
            }
        }
    }

    fun handleNetworkCall(response: GenericApiResponse<ResponseObject>){

        when(response){
            is ApiSuccessResponse ->{
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse ->{
                println("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse ->{
                println("DEBUG: NetworkBoundResource: Request returned NOTHING (HTTP 204)")
                onReturnError("HTTP 204. Returned NOTHING.")
            }
        }
    }

    fun onReturnError(message: String){
        result.value = DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>

}
