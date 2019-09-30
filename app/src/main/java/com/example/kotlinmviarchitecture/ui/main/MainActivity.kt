package com.example.kotlinmviarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmviarchitecture.R

class MainActivity : AppCompatActivity() {

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
