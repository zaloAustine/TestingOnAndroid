package com.zalo.androidtesting.view.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.zalo.androidtesting.databinding.ActivityMainBinding
import com.zalo.androidtesting.view.state.MainIntent
import com.zalo.androidtesting.view.state.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.sendIntent(MainIntent.fetchAnimals)

        binding.refreshButton.setOnClickListener {
            viewModel.sendIntent(MainIntent.fetchAnimals)
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                Log.e("States", state.toString())
                renderViewState(state)
            }
        }
    }

    private fun renderViewState(state: MainState) {
        when (state) {
            is MainState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is MainState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.textView.text = state.names[0].name
                showToast("loaded Successfully")
            }

            is MainState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.textView.text = state.error
            }

            else -> {}
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
