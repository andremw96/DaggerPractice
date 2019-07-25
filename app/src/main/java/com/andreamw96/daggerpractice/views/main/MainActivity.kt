package com.andreamw96.daggerpractice.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
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

        testFragment()
    }

    private fun testFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PostsFragment())
            .commit()
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
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.nav_profile -> {

            }

            R.id.nav_posts -> {

            }
        }

        menuItem.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
