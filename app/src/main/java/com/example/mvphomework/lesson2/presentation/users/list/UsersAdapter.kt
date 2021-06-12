package com.example.mvphomework.lesson2.presentation.users.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvphomework.databinding.UserItemBinding
import com.example.mvphomework.lesson2.utils.images.ImageLoader

class UsersAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: ImageLoader<ImageView>
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }

        override fun setAvatar(avatarUrl: String) {
            imageLoader.loadInto(url = avatarUrl, container = binding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(adapterPosition)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder, position)

    override fun getItemCount() = presenter.getCount()

}