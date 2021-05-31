package com.example.mvphomework.lesson2.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.arguments
import com.example.mvphomework.databinding.FragmentUserBinding
import com.example.mvphomework.lesson2.data.user.GitHubUser
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), BackButtonListener, UserView {

    companion object {
        private const val ARGUMENT_USER = "user"

        fun newInstance(user: GitHubUser) =
            UserFragment().arguments(ARGUMENT_USER to user)
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val user: GitHubUser by lazy {
        arguments?.getParcelable(ARGUMENT_USER)!!
    }

    private val userPresenter by moxyPresenter {
        UserPresenter(
            user,
            MvpApplication.Navigation.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = userPresenter.backPressed()

    override fun showUser(gitHubUser: GitHubUser) {
        binding.tvUserName.text = gitHubUser.login
    }
}