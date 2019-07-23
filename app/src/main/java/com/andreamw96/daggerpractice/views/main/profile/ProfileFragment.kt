package com.andreamw96.daggerpractice.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.andreamw96.daggerpractice.views.auth.AuthResource
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
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

        subscribeObservers()
    }

    private fun subscribeObservers() {
        // why do this? remove observer dll
        // fragment has their own lifecycle, so we need to make sure that we remove the previous observer before assigning
        // the new one
        profileViewModel.getAuthentitacedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthentitacedUser().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }

}
