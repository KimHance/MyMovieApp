package com.navermovie.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.navermovie.presentation.R
import com.navermovie.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        initNavigation()
    }

    private fun initData() {
        viewModel.getDailyBoxOfficeList()
        viewModel.getWeeklyBoxOfficeList()
        viewModel.deleteCachedData(System.currentTimeMillis())
    }

    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        with(binding.bottomNavMain) {
            setupWithNavController(navController)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.searchFragment -> {
                        navController.navigate(R.id.searchFragment)
                    }
                    R.id.boxOfficeFragment -> {
                        navController.navigate(
                            R.id.boxOfficeFragment,
                            null,
                            NavOptions.Builder()
                                .setLaunchSingleTop(true)
                                .setPopUpTo(R.id.boxOfficeFragment, false)
                                .build()
                        )
                    }
                    R.id.bookMarkFragment -> {
                        navController.navigate(R.id.bookMarkFragment)
                    }
                }
                true
            }
        }
    }
}