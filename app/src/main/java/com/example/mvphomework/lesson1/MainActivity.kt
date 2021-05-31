package com.example.mvphomework.lesson1

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import com.example.mvphomework.R
import com.example.mvphomework.databinding.ActivityMainHomework1Binding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), CountersView {

    private lateinit var binding: ActivityMainHomework1Binding
    private val presenter: MainPresenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHomework1Binding.inflate(layoutInflater)
            .also { setContentView(it.root) }
            .apply {
                btnCounter1.setOnClickListener { presenter.incrementCounter1() }
                btnCounter2.setOnClickListener { presenter.incrementCounter2() }
                btnCounter3.setOnClickListener { presenter.incrementCounter3() }
            }
    }

    override fun showOnBoarding() = AlertDialog
        .Builder(this)
        .setMessage(R.string.onboarding_message)
        .create()
        .show()

    override fun showCounter1(counter: String) {
        binding.btnCounter1.text = counter
    }

    override fun showCounter2(counter: String) {
        binding.btnCounter2.text = counter
    }

    override fun showCounter3(counter: String) {
        binding.btnCounter3.text = counter
    }

    override fun showCounterMessage() = Toast
        .makeText(this, R.string.counter_message, LENGTH_SHORT)
        .show()
}