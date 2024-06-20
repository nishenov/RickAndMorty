package com.example.nurzhigit_ishenov_hw_3_mon_5

import retrofit2.Call
import com.example.nurzhigit_ishenov_hw_3_mon_5.models.BaseResponse
import retrofit2.http.GET

interface CartoonApiService {

    @GET("character")
    fun fetchCharacters() : Call<BaseResponse>
}