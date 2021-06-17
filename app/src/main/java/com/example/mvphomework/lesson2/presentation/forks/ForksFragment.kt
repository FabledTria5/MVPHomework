package com.example.mvphomework.lesson2.presentation.forks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvphomework.*
import com.example.mvphomework.databinding.FragmentForksBinding
import com.example.mvphomework.lesson2.data.fork.RetrofitForksRepository
import com.example.mvphomework.lesson2.data.network.RetrofitSource
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.example.mvphomework.lesson2.presentation.forks.forks_list.ForksAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatDialogFragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ForksFragment : MvpAppCompatDialogFragment(), ForksView, BackButtonListener {

    companion object {
        private const val REPOSITORY_NAME = "repository_name"
        private const val USER_NAME = "user_name"

        fun newInstance(repositoryName: String, userName: String) =
            ForksFragment().arguments(
                REPOSITORY_NAME to repositoryName,
                USER_NAME to userName
            )
    }

    private var _binding: FragmentForksBinding? = null
    private val binding get() = _binding!!

    private lateinit var forksAdapter: ForksAdapter

    private val userName by lazy {
        arguments?.getString(USER_NAME)!!
    }

    private val repositoryName by lazy {
        arguments?.getString(REPOSITORY_NAME)!!
    }

    private val forksPresenter by moxyPresenter {
        ForksPresenter(
            Pair(userName, repositoryName),
            RetrofitForksRepository(RetrofitSource.api),
            AndroidSchedulers.mainThread(),
            MvpApplication.Navigation.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.rvForks.apply {
            layoutManager = LinearLayoutManager(context)
            forksAdapter = ForksAdapter(forksPresenter.forksListPresenter)
            adapter = forksAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() = binding.forksLoading.show()

    override fun showError() = toast(getString(R.string.forks_error))

    override fun showForks() {
        forksAdapter.notifyDataSetChanged()
        with(binding) {
            forksLoading.hide()
            rvForks.show()
        }
    }

    override fun backPressed() = forksPresenter.backPressed()

}