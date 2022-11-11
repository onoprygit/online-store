package com.onopry.online_store_test_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.onopry.online_store_test_task.databinding.ActivityMainBinding
import com.onopry.online_store_test_task.screens.home.HomeFragment
import com.onopry.online_store_test_task.utils.gone
import com.onopry.online_store_test_task.utils.hide
import com.onopry.online_store_test_task.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.fragment_container)
        binding.bottomNavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment -> binding.bottomNavBar.gone()
                R.id.cartFragment -> binding.bottomNavBar.gone()
                else -> binding.bottomNavBar.show()
            }
        }
    }


}