package com.zalo.androidtesting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.zalo.androidtesting.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.userIntent.send(MainIntent.fetchAnimals)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when (state) {
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Animals -> {
                        binding.progressBar.visibility = View.GONE
                        binding.textView.text = state.animals[0].name
                    }

                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.textView.text = state.error
                    }

                    else -> {
                    }
                }
            }
        }
    }
}