package com.pritom.dutta.the.theater.ui.activitys

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pritom.assets.extension.setupWithNavController
import com.pritom.dutta.the.theater.R
import com.pritom.dutta.the.theater.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //setupBottomNavigationBar()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                com.pritom.movies.R.navigation.nav_movies,
                com.pritom.tv_show.R.navigation.nav_tv_series,
                com.pritom.settings.R.navigation.nav_settings
            )
        )

        //navController.navigateUp(appBarConfiguration)
        binding?.bottomNavigationBar?.setupWithNavController(navController)

        // reselect back to home destination
        binding?.bottomNavigationBar?.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.nav_movies -> {
                    navController.popBackStack(
                        com.pritom.movies.R.id.movieFragment, false
                    )
                }
                R.id.nav_tv_series -> {
                    navController.popBackStack(
                        com.pritom.tv_show.R.id.tvShowFragment, false
                    )
                }
                else -> {
                    navController.popBackStack(
                        com.pritom.settings.R.id.settingsFragment, false
                    )
                }
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed()
        }
    }
}