package com.admin.admineventmanagement

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.admin.admineventmanagement.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var fragmentContainerView: FragmentContainerView
//    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        BottomNavigationView = binding.navigationBottom
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigationBottom
            .setupWithNavController(navController)

//        BottomNavigationView.onNavigationItemSelected  { item ->
//            when(item.itemId) {
//                R.id.item1 -> {
//                    // Respond to navigation item 1 click
//                    true
//                }
//                R.id.item2 -> {
//                    // Respond to navigation item 2 click
//                    true
//                }
//                else -> false
//            }
//        }
//        BottomNavigationView.onNavigationItemReselected { item ->
//            when(item.itemId) {
//                R.id.item1 -> {
//                    // Respond to navigation item 1 reselection
//                }
//                R.id.item2 -> {
//                    // Respond to navigation item 2 reselection
//                }
//            }
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.bottom_nav_bar, menu)
//        return true
//    }
}