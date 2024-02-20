package com.app.facts.di.facts

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent


@FactScope
@DefineComponent(parent = SingletonComponent::class)
interface FactsComponent {
}