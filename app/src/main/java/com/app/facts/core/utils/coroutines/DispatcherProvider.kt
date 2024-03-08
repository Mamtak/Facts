package com.app.facts.core.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

     fun default (): CoroutineDispatcher=Dispatchers.Default
     fun io ():CoroutineDispatcher=Dispatchers.IO
     fun main ():CoroutineDispatcher=Dispatchers.Main

}