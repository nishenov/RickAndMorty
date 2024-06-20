package com.example.nurzhigit_ishenov_hw_3_mon_5

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nurzhigit_ishenov_hw_3_mon_5.models.BaseResponse
import com.example.nurzhigit_ishenov_hw_3_mon_5.models.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val START_INDEX = 1

class CartoonPagingSource(): PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentKey = params.key ?: START_INDEX
            val previousKey = if (currentKey == START_INDEX) null else currentKey.minus(1)
            val response = fetchCharacters()
            LoadResult.Page(
                data = response,
                prevKey = previousKey,
                nextKey = currentKey.plus(1)
            )
        }
        catch (e:IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    private fun fetchCharacters(): List<Character>{
        var mResponse: List<Character> = emptyList()
        (
                RetrofitService.api.fetchCharacters().enqueue(object : Callback<BaseResponse>{
                    override fun onResponse(p0: Call<BaseResponse>, responce: Response<BaseResponse>) {
                        mResponse = responce.body()?.characters ?: emptyList()
                    }

                    override fun onFailure(p0: Call<BaseResponse>, p1: Throwable) {
                        mResponse = emptyList()
                    }
                }))
        return mResponse
    }
}