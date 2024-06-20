package com.example.nurzhigit_ishenov_hw_3_mon_5

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.nurzhigit_ishenov_hw_3_mon_5.databinding.ActivityMainBinding
import com.example.nurzhigit_ishenov_hw_3_mon_5.models.Character

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy{
        CartoonPagingAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.rvMain.adapter = adapter
        fetchCharacters().observe(this){data->
            adapter.submitData(this.lifecycle,data)
        }
    }


    private fun fetchCharacters(): LiveData<PagingData<Character>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CartoonPagingSource()
            }
        ).liveData
    }
}