package com.app.facts.ui

import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.facts.data.FactsRepository
import com.app.facts.data.repo.DataError
import com.app.facts.data.repo.Success
import com.app.facts.databinding.ActivityMainBinding
import com.app.facts.di.facts.FactsComponentManager
import com.app.facts.di.facts.FactsEntryPoint
import com.app.facts.ui.adapter.FactsListAdapter
import com.app.facts.ui.base.BaseActivity
import com.app.facts.utils.observe
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){
    @Inject
    lateinit var factsComponentManager: FactsComponentManager
    private lateinit var factsRepository: FactsRepository
    private lateinit var factsAdapter: FactsListAdapter

    val viewModel: MainActivityViewModel by viewModels {
        factsRepository = EntryPoints.get(
            factsComponentManager.getComponent(),
            FactsEntryPoint::class.java
        ).getFactsRepo()
        ViewModelFactory(factsRepository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpListUI()
        observeData()

        viewModel.loadingFacts()
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            observe(viewModel.factsResponse) {
                when (it) {
                    is DataError -> {
                        binding.pBar.visibility = GONE
                        Toast.makeText(this@MainActivity, it.errorMessage, Toast.LENGTH_LONG).show()
                    }
                    is Success -> {
                        binding.pBar.visibility = GONE
                        it.result?.facts?.let { it1 ->  setData(ArrayList(it1)) }
                    }


                    else -> {}
                }
            }
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    /** This is to set up the recyclerview */
    private fun setUpListUI() {
        factsAdapter = FactsListAdapter(arrayListOf())
        binding.factsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = factsAdapter
        }
    }

    fun setData(listItems: ArrayList<String>){
        // this creates a vertical layout Manager
        factsAdapter.updateChannelList(listItems)
  }
}