package com.example.testmagangandroiddot

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmagangandroiddot.databinding.ItemImageBinding

class Adapters : RecyclerView.Adapter<Adapters.ViewHolder>() {

    private val listGambarku = ArrayList<Gambar>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(list: ArrayList<Gambar>) {
        listGambarku.clear()
        listGambarku.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listGambarku.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGambarku[position])
    }

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gambar: Gambar) {
            binding.gambar = gambar
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(gambar) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Gambar)
    }

    object Bingkai {
        @JvmStatic
        @BindingAdapter("bingkai")
        fun bingkai(view: ImageView, gamber: String) {
            Glide.with(view.context)
                .load(gamber)
                .placeholder(R.drawable.placeholder)
                .into(view)
        }
    }
}