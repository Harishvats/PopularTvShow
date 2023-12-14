package com.harish.core.common.ui.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVIContract<ViewState, ViewIntent, SideEffect> {
    fun sendEvent(viewIntent: ViewIntent)
    fun createInitialState(): ViewState

    val viewState: StateFlow<ViewState>

    val sideEffect: Flow<SideEffect>

}