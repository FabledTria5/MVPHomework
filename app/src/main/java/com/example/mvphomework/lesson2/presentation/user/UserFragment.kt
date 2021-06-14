package com.example.mvphomework.lesson2.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.R
import com.example.mvphomework.arguments
import com.example.mvphomework.databinding.FragmentUserBinding
import com.example.mvphomework.lesson2.data.db.GitHubDatabase
import com.example.mvphomework.lesson2.data.retrofit.network.RetrofitSource
import com.example.mvphomework.lesson2.data.retrofit.repository.RetrofitRepositoriesRepo
import com.example.mvphomework.lesson2.data.retrofit.user.GitHubUser
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.presentation.user.repos_list.RepositoryAdapter
import com.example.mvphomework.lesson2.utils.images.GlideImageLoader
import com.example.mvphomework.lesson2.utils.network.AndroidNetworkStatus
import com.example.mvphomework.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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

    private val glideImageLoader by lazy { GlideImageLoader() }

    private val userPresenter by moxyPresenter {
        UserPresenter(
            user,
            AndroidSchedulers.mainThread(),
            RetrofitRepositoriesRepo(
                RetrofitSource.api,
                AndroidNetworkStatus(requireContext()),
                GitHubDatabase.getDatabase(requireContext())
            ),
            MvpApplication.Navigation.router,
            AndroidScreens()
        )
    }

    private lateinit var repositoryAdapter: RepositoryAdapter

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

    override fun init() {
        binding.rvUserRepositories.apply {
            layoutManager = LinearLayoutManager(context)
            repositoryAdapter = RepositoryAdapter(userPresenter.repositoriesPresenter)
            adapter = repositoryAdapter
        }
    }

    override fun backPressed() = userPresenter.backPressed()

    override fun showUser(gitHubUser: GitHubUser) {
        with(binding) {
            tvUserName.text = gitHubUser.login
            glideImageLoader.loadInto(gitHubUser.avatar_url, ivUserAvatar)
        }
    }

    override fun showError() = toast(getString(R.string.repos_error))

    override fun updateList() = repositoryAdapter.notifyDataSetChanged()
}