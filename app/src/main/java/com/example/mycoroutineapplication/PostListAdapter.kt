package com.example.mycoroutineapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mycoroutineapplication.databinding.ItemPostBinding

class PostListAdapter(val posts: LiveData<ArrayList<Post>>)
    : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post?) {
            post?.let {
                binding.txtTitle.text = it.title
                binding.txtBody.text = it.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts.value?.getOrNull(position))
    }

    override fun getItemCount(): Int = posts.value?.size ?: 0
}