package com.example.coroutineszerotohero





import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("/api/a83e4dcabe9e4d1014d42c002c3f977b/search({name}")
    fun getSuperheroes(@Path("name") superheroName:String): Response<SuperHeroDataResponse>
}

data class SuperHeroDataResponse(@SerializedName("response") val response:String)