package com.andreamw96.daggerpractice.network.main

import com.andreamw96.daggerpractice.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    // posts?userId=1 -> klo tanda tanya pake @Query
    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>

}