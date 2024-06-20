package com.example.nurzhigit_ishenov_hw_3_mon_5.models


import com.google.gson.annotations.SerializedName


data class BaseResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("characters")
    val characters: List<Character>
)