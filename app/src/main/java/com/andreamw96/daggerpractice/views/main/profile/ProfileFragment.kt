package com.andreamw96.daggerpractice.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var viewModelProvidersFactory : ViewModelProvidersFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logd("ProfileFragment was created")
        profileViewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(ProfileViewModel::class.java)
    }



}
