package com.example.rxjava.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjava.R
import com.example.rxjava.utils.BannerUtils
import com.example.rxjava.databinding.ItemHomeGenresListBinding
import com.example.rxjava.databinding.ItemHomePosterBinding
import com.example.rxjava.home.adapters.models.DisplayableItem
import com.example.rxjava.home.adapters.models.HomeGenreEntity
import com.example.rxjava.home.adapters.models.HomePosterEntity
import java.lang.IllegalArgumentException

class DisplayableAdapter(private val context: Context) :
    ListAdapter<DisplayableItem, RecyclerView.ViewHolder>(DisplayableDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            POSTER_TYPE -> PrevPostersViewHolder(
                ItemHomeGenresListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ADVERTISING_TYPE -> AdvertisingViewHolder(
                ItemHomePosterBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val element = currentList[position]
        when (holder) {
            is PrevPostersViewHolder -> holder.bind(element as HomeGenreEntity)
            is AdvertisingViewHolder -> holder.bind(element as HomePosterEntity)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is HomeGenreEntity -> POSTER_TYPE
            is HomePosterEntity -> ADVERTISING_TYPE
            else -> throw IllegalArgumentException()
        }
    }

    inner class AdvertisingViewHolder(
        private val binding: ItemHomePosterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: HomePosterEntity) = with(binding) {
            ivImagePoster.setOnClickListener {
                BannerUtils.showToast(
                    context.getString(R.string.ukraine_message),
                    binding.root.context
                )
            }
        }
    }


    inner class PrevPostersViewHolder(
        private val binding: ItemHomeGenresListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: HomeGenreEntity) = with(binding) {
            val adapter = ItemHomeGenresAdapter()
            rvGenreList.adapter = adapter
            rvGenreList.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
            adapter.submitList(model.list)

            tvGenreTitle.text = model.title

        }
    }

    object DisplayableDiffCallback : DiffUtil.ItemCallback<DisplayableItem>() {
        override fun areItemsTheSame(
            oldItem: DisplayableItem,
            newItem: DisplayableItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: DisplayableItem,
            newItem: DisplayableItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        const val ADVERTISING_TYPE = 1
        const val POSTER_TYPE = 2
    }

}