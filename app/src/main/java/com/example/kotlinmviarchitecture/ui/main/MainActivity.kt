package com.example.kotlinmviarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmviarchitecture.R
import com.example.kotlinmviarchitecture.ui.DataStateListener
import com.example.kotlinmviarchitecture.util.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DataStateListener {

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {// its like if datastate != null

            // handle loading
            showProgressBar(it.loading)

            // handle message
            it.message?.let { message ->
                showToast(message)
            }
        }
    }

    fun showProgressBar(isVisible: Boolean) {
        if(isVisible){
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        showMainFragment();
    }

    fun showMainFragment(){
        val mainFragment: MainFragment =
            MainFragment();
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragment, "MainFragment")
            .commit();
    }

}
