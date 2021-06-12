package com.example.mvphomework.lesson2.presentation.user.repos_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvphomework.databinding.ItemRepositoryBinding

class RepositoryAdapter(
    private val presenter: IRepositoryListPresenter,
) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root), RepositoryItemView {

        override fun setName(repositoryName: String) {
            binding.tvRepoName.text = repositoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepositoryViewHolder(
        ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(adapterPosition)
        }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) =
        presenter.bindView(holder, position)

    override fun getItemCount() = presenter.getCount()

}