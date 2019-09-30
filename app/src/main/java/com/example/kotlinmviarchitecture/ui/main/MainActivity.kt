package com.example.kotlinmviarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmviarchitecture.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showMainFragment(){
        val mainFragment: MainFragment =
            MainFragment();
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, mainFragment, "MainFragment")
            .commit();
    }

}
