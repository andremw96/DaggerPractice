package com.andreamw96.daggerpractice.views.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.andreamw96.daggerpractice.models.User
import com.andreamw96.daggerpractice.network.auth.AuthApi
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    private var TAG = "AuthViewModel"

    init {
        Log.d(TAG, "Auth View Model is working")

        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : io.reactivex.Observer<User> {
                override fun onSubscribe(d: Disposable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: User) {
                    Log.d(TAG, "onNext: ${t.email}")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: $e")
                }

                override fun onComplete() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

}