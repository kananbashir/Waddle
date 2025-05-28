package com.waddleup.home.viewmodel

import com.waddleup.core.base.util.DispatchersProvider
import com.waddleup.core.base.viewmodel.BaseViewModel
import com.waddleup.home.viewmodel.state.HomeIntent
import com.waddleup.home.viewmodel.state.HomeState

/**
 * Created on 5/18/2025
 * @author Kanan Bashir
 */

class HomeViewModel(
    dispatchersProvider: DispatchersProvider
): BaseViewModel<HomeState, HomeIntent>(dispatchersProvider) {
    override val initialState: HomeState
        get() = HomeState()


}