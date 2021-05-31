package com.example.mvphomework.lesson2.presentation

import android.os.Bundle
import com.example.mvphomework.MvpApplication
import com.example.mvphomework.R
import com.example.mvphomework.lesson2.navigation.AndroidScreens
import com.example.mvphomework.lesson2.navigation.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main_homework2), MainView {
    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    private val presenter by moxyPresenter {
        MainPresenter(
            MvpApplication.Navigation.router,
            AndroidScreens()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState
            ?: MvpApplication.Navigation.router.newRootScreen(AndroidScreens().users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        MvpApplication.Navigation.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        MvpApplication.Navigation.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}