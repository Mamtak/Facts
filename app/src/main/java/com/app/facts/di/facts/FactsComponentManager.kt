package com.app.facts.di.facts

import com.app.facts.data.FactsRepository
import com.app.facts.domain.repo.ApiRepository
import com.app.facts.core.utils.coroutines.DispatcherProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class FactsComponentManager @Inject constructor(
    private val appDispatcherProvider: DispatcherProvider,
    private val factComponentProvider: Provider<FactsComponentBuilder>,
    private val apiRepository: ApiRepository
)

{

    var factsComponent: FactsComponent? = null

    fun getComponent():FactsComponent{
        if(factsComponent==null){
            val factRepository = FactsRepository(appDispatcherProvider, apiRepository)
            factsComponent=  factComponentProvider.get().bindFactRepo(factRepository).build()

        }

        return factsComponent!!
    }

    fun destroyFactComponent() {
        factsComponent = null
    }
}