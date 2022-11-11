package com.onopry.online_store_test_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onopry.online_store_test_task.screens.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeFragment)
            .commit()

    }
}