package com.example.nurzhigit_ishenov_hw_3_mon_5.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Origin(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)