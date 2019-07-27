package com.andreamw96.daggerpractice.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.andreamw96.daggerpractice.BaseActivity
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.views.main.post.PostsFragment
import com.andreamw96.daggerpractice.views.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

        init()
    }

    private fun init() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
            }

            R.id.home -> {
                return if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.nav_profile -> {

                // clear the backstack whenever we navigate to the profile screen
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                Navigation.findNavController(this, R.id.nav_host_fragment).
                    navigate(R.id.profileScreen,
                        null,
                        navOptions)
            }

            R.id.nav_posts -> {
                if(isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen)
                }
            }
        }

        menuItem.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination: Int) : Boolean {
        // if the destination is currently ON then don't do the transaction
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawer_layout)
    }
}