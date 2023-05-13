package com.example.stackexchange.network

import com.example.stackexchange.model.HomeData.Home_Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackApi   {
    companion object{
        const val BASE_URL = "https://api.stackexchange.com/2.3/"
    }
    @GET("questions")
    suspend fun getHomeData(@Query("order")oreder:String,@Query("sort")sort:String,@Query("site")site:String) : Home_Data

    @GET("search")
    suspend fun getSearchData(@Query("order")oreder:String,@Query("intitle")title:String,@Query("sort")sort:String,@Query("site")site:String) : Home_Data
}