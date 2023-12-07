package com.harish.core.common.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun sendEvent(viewIntent: ViewIntent)
}
// TODO: Prefer delagation over inheritance in kotlin.
