package com.example.mvphomework.lesson2.presentation.forks.forks_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvphomework.databinding.ItemForkBinding

class ForksAdapter(private val presenter: IForksPresenter) :
    RecyclerView.Adapter<ForksAdapter.ForksViewHolder>() {

    inner class ForksViewHolder(private val binding: ItemForkBinding) :
        RecyclerView.ViewHolder(binding.root), ForksItemView {

        override fun setFork(forkName: String) {
            binding.tvForkName.text = forkName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForksViewHolder(
        ItemForkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ForksViewHolder, position: Int) = presenter.bindView(
        view = holder,
        position = position
    )

    override fun getItemCount() = presenter.getCount()

}