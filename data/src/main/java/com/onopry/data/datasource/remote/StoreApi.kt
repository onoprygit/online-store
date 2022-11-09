package com.onopry.data.datasource.remote

import com.onopry.data.models.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

    @GET("./654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getBannersAndProducts(): Response<HomeResponse>

    @GET("./6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): Response<Any>
}