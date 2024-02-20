package com.app.facts.di.facts

import com.app.facts.data.FactsRepository
import dagger.BindsInstance
import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface FactsComponentBuilder {
    fun bindFactRepo(@BindsInstance factsRepository: FactsRepository): FactsComponentBuilder
    fun build(): FactsComponent?
}