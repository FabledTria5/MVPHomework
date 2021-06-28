package com.example.mvphomework.lesson2.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.R.layout.fragment_user
import com.example.mvphomework.arguments
import com.example.mvphomework.databinding.FragmentUserBinding
import com.example.mvphomework.lesson2.data.datasource.repository.IRepoRepository
import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.navigation.IScreens
import com.example.mvphomework.lesson2.presentation.di_classes.DaggerFragment
import com.example.mvphomework.lesson2.presentation.user.repos_list.RepositoryAdapter
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.example.mvphomework.loadImage
import com.example.mvphomework.toast
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : DaggerFragment(fragment_user), BackButtonListener, UserView {

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var usersRepo: IRepoRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    companion object {
        private const val ARGUMENT_USER = "user"

        fun newInstance(user: GitHubUser) = UserFragment().apply {
            arguments(ARGUMENT_USER to user)
        }
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val user: GitHubUser by lazy {
        arguments?.getParcelable(ARGUMENT_USER)!!
    }

    private val userPresenter by moxyPresenter {
        UserPresenter(user, schedulers, usersRepo, router, screens)
    }

    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

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
            ivUserAvatar.loadImage(gitHubUser.avatar)
        }
    }

    override fun showError(t: Throwable) = toast(t.toString())

    override fun updateList() = repositoryAdapter.notifyDataSetChanged()
}