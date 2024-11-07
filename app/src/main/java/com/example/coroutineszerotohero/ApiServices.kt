// ApiServices.kt
package com.example.coroutineszerotohero

import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>
}
