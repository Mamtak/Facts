package com.app.facts.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.facts.core.common.DataError
import com.app.facts.core.common.Success
import com.app.facts.data.FactsRepository
import com.app.facts.di.facts.FactsComponentManager
import com.app.facts.di.facts.FactsEntryPoint
import com.app.facts.presentation.base.BaseComponentActivity
import com.app.facts.presentation.components.FactsItem
import com.app.facts.core.utils.observe
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : BaseComponentActivity<MainActivityViewModel>() {
    @Inject
    lateinit var factsComponentManager: FactsComponentManager
    private lateinit var factsRepository: FactsRepository

    val viewModel: MainActivityViewModel by viewModels {
        factsRepository = EntryPoints.get(
            factsComponentManager.getComponent(),
            FactsEntryPoint::class.java
        ).getFactsRepo()
        ViewModelFactory(factsRepository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingFacts()
    }

    @Composable
    override fun ProvideCompose() {
        val factsListResponse = remember { mutableStateListOf<String>() }
        FactsCompose {
            observe(viewModel.factsResponse) {
                when (it) {
                    is DataError -> {
                        Toast.makeText(this@MainActivity, it.errorMessage, Toast.LENGTH_LONG).show()
                    }

                    is Success -> {
                        it.result.let { it1 ->
                            if (it1 != null) {
                                factsListResponse.swapList(it1.facts)
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
        LazyColumn {
            items(setData(factsListResponse)) {
                FactsItem(it)
            }
        }
    }

    @Composable
    override fun ProvideComposeLightPreview() {

    }

//    @Composable
//    fun FactsItem(fact: String) {
//        Row {
//            Log.e("textttttt::::", ":::::" + fact)
//            Text(
//                text = fact,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//    }

    fun setData(listItems: List<String>): List<String> {
        return listItems
    }

    @Composable
    private fun FactsCompose(ChildrenCompose: @Composable () -> Unit) {

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            ChildrenCompose()

        }
    }

    fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
        clear()
        addAll(newList)
    }
}
