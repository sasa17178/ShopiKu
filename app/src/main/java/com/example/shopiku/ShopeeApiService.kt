package com.example.shopiku

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ShopeeApiService {
    @GET("rest/v1/products")
    suspend fun getProducts(
        @Header("apikey") apiKey: String,
        @Header("Authorization") authorization: String
    ): Response<List<Product>>
}