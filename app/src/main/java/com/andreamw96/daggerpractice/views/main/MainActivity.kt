package com.andreamw96.daggerpractice.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andreamw96.daggerpractice.BaseActivity
import com.andreamw96.daggerpractice.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
    }
}
