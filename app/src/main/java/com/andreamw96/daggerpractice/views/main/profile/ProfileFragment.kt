package com.andreamw96.daggerpractice.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andreamw96.daggerpractice.R
import dagger.android.support.DaggerFragment

class ProfileFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }




}
