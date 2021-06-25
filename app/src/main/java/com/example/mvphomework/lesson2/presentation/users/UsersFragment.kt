package com.example.mvphomework.lesson2.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.R.layout.fragment_users
import com.example.mvphomework.databinding.FragmentUsersBinding
import com.example.mvphomework.lesson2.data.datasource.user.IUserRepository
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.navigation.IScreens
import com.example.mvphomework.lesson2.presentation.di_classes.DaggerFragment
import com.example.mvphomework.lesson2.presentation.users.list.UsersAdapter
import com.example.mvphomework.lesson2.schedulers.Schedulers
import com.example.mvphomework.toast
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : DaggerFragment(fragment_users), UsersView, BackButtonListener {

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userRepository: IUserRepository

    @Inject
    lateinit var screens: IScreens

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(
            schedulers = schedulers,
            router = router,
            usersRepo = userRepository,
            screens = screens
        )
    }

    private lateinit var usersAdapter: UsersAdapter

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

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

    override fun showError(t: Throwable) = toast(t.toString())

    override fun backPressed() = presenter.backPressed()
}