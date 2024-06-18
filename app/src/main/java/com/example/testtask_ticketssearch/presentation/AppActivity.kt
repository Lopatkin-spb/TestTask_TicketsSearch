package com.example.testtask_ticketssearch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.app.TTTSApp
import com.example.testtask_ticketssearch.databinding.ActivityAppBinding
import com.example.testtask_ticketssearch.di.AppComponent
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as TTTSApp).appComponent
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //TODO: correct visible to theme
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_app)

        setupActionBar(navController)
        setupBottomMenu(navController)
    }

    private fun setupActionBar(navController: NavController) {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_airtickets,
                R.id.navigation_hotels,
                R.id.navigation_shorter,
                R.id.navigation_subscriptions,
                R.id.navigation_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomMenu(navController: NavController) {
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }

}