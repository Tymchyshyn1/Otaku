package com.example.otaku.details.info.adapters.studios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.otaku.databinding.LayoutDetailsStudiosInfoBinding

class ContainerStudiosAdapter :
    ListAdapter<ContainerStudios, ContainerStudiosAdapter.ParentStudiosViewHolder>(
        ParentStudiosDiffCallback
    ) {

    private val studiosAdapter by lazy { StudiosAdapter() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentStudiosViewHolder {
        val binding =
            LayoutDetailsStudiosInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.rvStudios.layoutManager =
            LinearLayoutManager(parent.context, RecyclerView.HORIZONTAL, false)
        binding.rvStudios.adapter = studiosAdapter

        return ParentStudiosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentStudiosViewHolder, position: Int) =
        holder.bind(currentList[position])


    override fun getItemCount(): Int = currentList.size

    inner class ParentStudiosViewHolder(
        private val binding:
        LayoutDetailsStudiosInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ContainerStudios) = with(binding) {

            studiosAdapter.submitList(item.list)
        }
    }


    object ParentStudiosDiffCallback : DiffUtil.ItemCallback<ContainerStudios>() {
        override fun areItemsTheSame(
            oldItem: ContainerStudios,
            newItem: ContainerStudios
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ContainerStudios,
            newItem: ContainerStudios
        ): Boolean {
            return oldItem == newItem
        }
    }
}