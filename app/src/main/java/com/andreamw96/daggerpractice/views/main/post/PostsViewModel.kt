package com.andreamw96.daggerpractice.views.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.SessionManager
import com.andreamw96.daggerpractice.models.Post
import com.andreamw96.daggerpractice.network.main.MainApi
import com.andreamw96.daggerpractice.utils.logd
import com.andreamw96.daggerpractice.utils.loge
import com.andreamw96.daggerpractice.views.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi): ViewModel() {

    private var posts : MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    init {
        logd("PostsViewModel is working")
    }

    fun observerPosts() : LiveData<Resource<List<Post>>> {
        posts = MediatorLiveData()
        posts.value = Resource.loading(null)

        val source : LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id)
                .onErrorReturn {
                    val post = Post()
                    post.id = -1
                    val posts = mutableListOf<Post>()
                    posts.add(post)
                    posts
                }
                .map { posts ->
                    //if(posts.size > 0) {
                        if(posts[0].id == -1) {
                            Resource.error("Something went wrong", posts)
                        } else {
                            Resource.success(posts)
                        }
                   // }
                }
                .subscribeOn(Schedulers.io())
        )

        posts.addSource(source, Observer {
            posts.value = it
            posts.removeSource(source)
        })

        return posts
    }

}