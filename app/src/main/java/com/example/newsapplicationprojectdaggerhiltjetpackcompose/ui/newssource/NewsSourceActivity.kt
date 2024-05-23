package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.MyNewsApplication
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiSource
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.databinding.ActivityNewsSourcesBinding
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.component.DaggerActivityComponent
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.di.module.ActivityModule
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceActivity : AppCompatActivity() {

    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var adapter: NewsSourceAdapter

    private lateinit var binding: ActivityNewsSourcesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.newsSourcesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsSourceViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.newsSourcesProgressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.newsSourcesRecyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.newsSourcesProgressBar.visibility = View.VISIBLE
                            binding.newsSourcesRecyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            binding.newsSourcesProgressBar.visibility = View.GONE
                            Toast.makeText(
                                this@NewsSourceActivity,
                                it.message,
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(sourcesList: List<ApiSource>) {
        adapter.addData(sourcesList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as MyNewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build()
            .inject(this)
    }
}