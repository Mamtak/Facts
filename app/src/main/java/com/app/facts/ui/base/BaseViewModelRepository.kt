package com.app.facts.ui.base


open class BaseViewModelRepository<T: BaseRepository>(repo : T )
    : BaseViewModel<T>(repo){


        fun getRepository() : T{

            return anyType
        }


}