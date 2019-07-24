package com.andreamw96.daggerpractice.views.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class PostsFragment : DaggerFragment() {

    lateinit var viewModel : PostsViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(PostsViewModel::class.java)
    }
}
