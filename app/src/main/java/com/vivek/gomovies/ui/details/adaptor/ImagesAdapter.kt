package com.vivek.gomovies.ui.details.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vivek.gomovies.databinding.ItemImageBinding
import com.vivek.gomovies.model.PersonImage

class ImagesAdapter : ListAdapter<PersonImage, ImagesAdapter.ImageViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: PersonImage) {
            binding.imageView.load(image.fullImagePath)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PersonImage>() {
        override fun areItemsTheSame(oldItem: PersonImage, newItem: PersonImage): Boolean {
            return oldItem.file_path == newItem.file_path
        }

        override fun areContentsTheSame(oldItem: PersonImage, newItem: PersonImage): Boolean {
            return oldItem == newItem
        }
    }
}
