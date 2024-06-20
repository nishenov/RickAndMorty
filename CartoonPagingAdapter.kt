package com.example.nurzhigit_ishenov_hw_3_mon_5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.nurzhigit_ishenov_hw_3_mon_5.databinding.ItemCharacterBinding
import com.example.nurzhigit_ishenov_hw_3_mon_5.models.Character

class CartoonPagingAdapter: PagingDataAdapter<Character, CharacterViewHolder>(CharacterItemCallBack()) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        character?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}

class CharacterItemCallBack: DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}

class CharacterViewHolder(val binding: ItemCharacterBinding): ViewHolder(binding.root) {
    fun bind(character: Character){
        Glide.with(binding.itemImgCharacter).load(character.image).into(binding.itemImgCharacter)
    }
}
