package com.nasa.gallery.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasa.gallery.data.model.Image
import com.nasa.gallery.databinding.ImageGridItemBinding

class ImageAdapter(private val items: List<Image>,
                   private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageGridItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position, items[position])

    inner class ViewHolder(private val binding: ImageGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: Image) {
            binding.image = item
            binding.root.setOnClickListener {
                onItemClick(position)
            }
            binding.executePendingBindings()
        }
    }
}
