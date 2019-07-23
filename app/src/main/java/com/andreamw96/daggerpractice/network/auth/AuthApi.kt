package com.andreamw96.daggerpractice.network.auth

import com.andreamw96.daggerpractice.models.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>

}