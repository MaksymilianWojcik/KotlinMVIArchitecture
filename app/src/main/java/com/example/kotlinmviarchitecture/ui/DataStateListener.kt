package com.example.kotlinmviarchitecture.ui

import com.example.kotlinmviarchitecture.util.DataState

interface DataStateListener {
    fun onDataStateChange(dataState: DataState<*>?) // * - whatever type (type invariant)
}