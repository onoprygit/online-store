package com.onopry.online_store_test_task.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.onopry.online_store_test_task.R
import com.onopry.online_store_test_task.databinding.ActivityMainBinding
import com.onopry.online_store_test_task.utils.gone
import com.onopry.online_store_test_task.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val sharedCartViewModel: SharedCartViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        val menuItem = binding.bottomNavBar.menu[1]
        val badge = binding.bottomNavBar.getOrCreateBadge(menuItem.itemId)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedCartViewModel.cartItemsCount.collect{
                    if (it > 0) {
                        badge.number = it
                        badge.isVisible = true
                    } else {
                        badge.isVisible = false
                    }
                }
            }
        }
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

    private fun addBadge(count : Int) {
//        val badge: BadgeDrawable = binding.bottomNavBar.getOrCreateBadge(
//            R.id.cartFragment)
        val menuItem = binding.bottomNavBar.menu[1]
        val badge = binding.bottomNavBar.getOrCreateBadge(menuItem.itemId)
        badge.number = count
        badge.isVisible = true
    }

}