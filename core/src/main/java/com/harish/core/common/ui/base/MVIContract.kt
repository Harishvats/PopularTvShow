package com.harish.core.common.ui.base

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVIContract<ViewState, ViewIntent, SideEffect> {
    fun sendEvent(viewIntent: ViewIntent)
    fun createInitialState(): ViewState

    val viewState: StateFlow<ViewState>

    val sideEffect: SharedFlow<SideEffect>

}