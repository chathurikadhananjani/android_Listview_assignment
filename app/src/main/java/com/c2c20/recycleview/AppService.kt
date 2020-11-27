package com.c2c20.recycleview

import retrofit2.Call
import retrofit2.http.GET

interface AppService {
    @GET("/users")
    fun fetchAllUsers(): Call<List<User>>

}