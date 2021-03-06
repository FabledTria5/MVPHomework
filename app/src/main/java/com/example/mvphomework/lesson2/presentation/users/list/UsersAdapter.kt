package com.example.mvphomework.lesson2.presentation.users.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvphomework.databinding.UserItemBinding
import com.example.mvphomework.loadImage

class UsersAdapter(
    private val presenter: IUserListPresenter,
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override fun setLogin(text: String) {
            binding.tvLogin.text = text
        }

        override fun setAvatar(avatarUrl: String) {
            binding.ivAvatar.loadImage(avatarUrl)
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
