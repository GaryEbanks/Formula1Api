package com.example.formula1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDriverData(@Url url:String): Response<MainModel>
}