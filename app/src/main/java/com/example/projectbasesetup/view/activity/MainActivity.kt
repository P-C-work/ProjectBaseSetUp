package com.example.projectbasesetup.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectbasesetup.databinding.ActivityMainBinding
import com.example.projectbasesetup.models.CatsDataModel
import com.example.projectbasesetup.view.adapters.CatsListAdapter
import com.example.projectbasesetup.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CatsListAdapter.CatDetailCallBack {
    private lateinit var binding: ActivityMainBinding
    private lateinit var catsListAdapter: CatsListAdapter
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        setObservers()
        viewModel.getCatsData()
    }

    private fun setUpViews() {
        with(binding) {
            rvCats.apply {
                catsListAdapter = CatsListAdapter(this@MainActivity, this@MainActivity)
                val lManager = GridLayoutManager(this@MainActivity, 2)
//        lManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE;
                adapter = catsListAdapter
                layoutManager = lManager
            }
        }
    }


    private fun setObservers() {
        with(viewModel) {
            catsData.observe(this@MainActivity) {
                catsListAdapter.submitList(it)
            }

            isLoading.observe(this@MainActivity) {
                if (it) {
                    binding.mainProgressBar.visibility = View.VISIBLE
                } else {
                    binding.mainProgressBar.visibility = View.GONE
                }
            }


            error.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun callForCatDetails(cats: CatsDataModel) {
        startActivity(Intent(this, FullViewActivity::class.java).apply {
            putExtra(
                "URL",
                cats.url
            )
        })
    }

}