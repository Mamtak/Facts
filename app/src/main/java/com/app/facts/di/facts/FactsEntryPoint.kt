package com.app.facts.di.facts

import com.app.facts.data.FactsRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(FactsComponent::class)
interface FactsEntryPoint {
    fun getFactsRepo(): FactsRepository
}