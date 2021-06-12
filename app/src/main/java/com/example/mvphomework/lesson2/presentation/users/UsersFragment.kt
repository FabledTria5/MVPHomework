package com.example.mvphomework.lesson2.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.R
import com.example.mvphomework.databinding.FragmentUsersBinding
import com.example.mvphomework.lesson2.data.user.GitHubUserRepository
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.presentation.users.list.UsersAdapter
import com.example.mvphomework.toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            UserInteractor(userRepository = GitHubUserRepository()),
            MvpApplication.Navigation.router
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
            usersAdapter = UsersAdapter(presenter.usersListPresenter)
            adapter = usersAdapter
        }
    }

    override fun updateList() = usersAdapter.notifyDataSetChanged()

    override fun showError() = toast(getString(R.string.error_message))

    override fun backPressed() = presenter.backPressed()
}