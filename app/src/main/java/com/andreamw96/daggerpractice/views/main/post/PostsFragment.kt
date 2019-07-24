package com.andreamw96.daggerpractice.views.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.daggerpractice.R
import com.andreamw96.daggerpractice.utils.VerticalSpacingItemDecoration
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.utils.loge
import com.andreamw96.daggerpractice.viewmodels.ViewModelProvidersFactory
import com.andreamw96.daggerpractice.views.main.Resource
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject
import kotlin.math.log


class PostsFragment : DaggerFragment() {

    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var viewModelProvidersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelProvidersFactory).get(PostsViewModel::class.java)

        initRecyclerView()
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.observerPosts().removeObservers(viewLifecycleOwner)
        viewModel.observerPosts().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                when(it.status) {
                    Resource.Status.LOADING -> {
                        logd("LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        logd("got the posts...")
                        it.data?.let { it -> adapter.setPosts(it) }
                    }
                    Resource.Status.ERROR -> {
                        loge("ERROR ${it.message}")
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = adapter
    }
}
