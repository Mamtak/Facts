package com.app.facts.presentation.base

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.facts.core.common.DataError
import com.app.facts.core.common.ResultState
import com.app.facts.core.common.Success
import com.app.facts.core.utils.GENERIC_ERROR
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel<T>(
    val anyType:T,
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val showDialogLoadingPrivate = MutableLiveData(false)

    val showMessageDialog = MutableLiveData<ResultState<String>>()

    private val onErrorDialogDismissPrivate = MutableLiveData<ResultState<Boolean>>()
    val onErrorDialogDismiss: LiveData<ResultState<Boolean>> get() = onErrorDialogDismissPrivate

    protected val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        showMessageDialog(DataError(GENERIC_ERROR))
    }

    fun showMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun showPostMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun hideMessageDialog(success: Success<String>) {
        showMessageDialog.value = success
    }

}