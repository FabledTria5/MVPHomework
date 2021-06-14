package com.example.mvphomework.lesson2.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.R
import com.example.mvphomework.databinding.FragmentUsersBinding
import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitSource
import com.example.mvphomework.lesson2.data.retrofit.user.RetrofitGithubUsersRepo
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.presentation.users.list.UsersAdapter
import com.example.mvphomework.lesson2.utils.images.GlideImageLoader
import com.example.mvphomework.lesson2.utils.network.AndroidNetworkStatus
import com.example.mvphomework.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(
                RetrofitSource.api,
                AndroidNetworkStatus(requireContext()),
                GitHubDatabase.getDatabase(requireContext())
            ),
            MvpApplication.Navigation.router,
            AndroidScreens()
        )
    }

    private lateinit var usersAdapter: UsersAdapter

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            usersAdapter = UsersAdapter(presenter.usersListPresenter, GlideImageLoader())
            adapter = usersAdapter
        }
    }

    override fun updateList() = usersAdapter.notifyDataSetChanged()

    override fun showError(t: Throwable) = toast(t.toString())

    override fun backPressed() = presenter.backPressed()
}