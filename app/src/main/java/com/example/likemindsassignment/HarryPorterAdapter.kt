package com.example.likemindsassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.likemindsassignment.data.HarryPoterResponse
import com.example.likemindsassignment.data.HarryPoterResponseItem
import com.example.likemindsassignment.databinding.ItemViewBinding
import com.squareup.picasso.Picasso

class HarryPorterAdapter(private val onItemClicked: (HarryPoterResponseItem) -> Unit) :
    ListAdapter<HarryPoterResponseItem, HarryPorterAdapter.HarryViewHolder>(ComparatorDiffUtil()) {

    inner class HarryViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(harryCharacter: HarryPoterResponseItem) {
            binding.root.setOnClickListener {
                onItemClicked.invoke(harryCharacter)
            }
            binding.harryPoterName.text = harryCharacter.fullName
            Picasso.get().load(harryCharacter.image).into(binding.harryPoterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarryViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HarryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HarryViewHolder, position: Int) {
        val character = getItem(position)
        character.let {
            holder.bind(it)
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<HarryPoterResponseItem>() {
        override fun areItemsTheSame(
            oldItem: HarryPoterResponseItem,
            newItem: HarryPoterResponseItem
        ): Boolean {
            return oldItem.index == newItem.index
        }

        override fun areContentsTheSame(
            oldItem: HarryPoterResponseItem,
            newItem: HarryPoterResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}