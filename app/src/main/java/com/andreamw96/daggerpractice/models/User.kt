package com.andreamw96.daggerpractice.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    @Expose
    var id : Int = -1,

    @SerializedName("username")
    @Expose
    var username : String = "",

    @SerializedName("email")
    @Expose
    var email : String = "",

    @SerializedName("website")
    @Expose
    var website : String
)