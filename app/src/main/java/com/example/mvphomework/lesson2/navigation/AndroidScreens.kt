package com.example.mvphomework.lesson2.navigation

import com.example.mvphomework.lesson2.data.model.GitHubUser
import com.example.mvphomework.lesson2.presentation.forks.ForksFragment
import com.example.mvphomework.lesson2.presentation.user.UserFragment
import com.example.mvphomework.lesson2.presentation.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(gitHubUser: GitHubUser) =
        FragmentScreen { UserFragment.newInstance(user = gitHubUser) }

    override fun forks(userName: String, repositoryName: String) =
        FragmentScreen {
            ForksFragment.newInstance(
                repositoryName = repositoryName,
                userName = userName
            )
        }

}