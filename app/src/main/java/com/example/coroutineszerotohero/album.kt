package com.example.coroutineszerotohero

// Album.kt

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)
