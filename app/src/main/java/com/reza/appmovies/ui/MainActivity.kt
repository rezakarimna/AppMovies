package com.reza.appmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.reza.appmovies.R
import com.reza.appmovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //binding
    lateinit var binding: ActivityMainBinding

    //navController
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavControllerAndSetBottomNavigation()
        showBottomNavigation()

    }

    private fun setNavControllerAndSetBottomNavigation() {
        binding.apply {
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
        }
    }

    private fun showBottomNavigation() {
        binding.apply {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment
                    || destination.id == R.id.detailFragment
                ) {
                    bottomNav.visibility = View.GONE
                } else {
                    bottomNav.visibility = View.VISIBLE
                }
            }
        }

    }

    /*override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }*/
}