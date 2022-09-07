package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import android.util.Log
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.getExplores
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStore
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

class ExploreCenterGetExploresReducer(override var store: ExploreCenterStoreImplementation?): ExploreCenterReducerImplementation(store) {
    private val _action: ExploreCenterAction = getExplores()
    override var action: ExploreCenterAction
        get() = _action
        set(value) {}

    override fun willUpdateView(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ): ExploreCenterState? {
        var newState: ExploreCenterState = state ?: ExploreCenterState()
        newState.isLoading = true
        return newState
    }

    override fun onUpdate(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        payload: Any?
    ): ExploreCenterState? {
        var newState: ExploreCenterState = state ?: ExploreCenterState()
        val response = payload as? ExploresReposonse
        response?.let {
            newState.exploresReposonse = it
        }
        newState.isLoading = false
        return newState
    }

    override fun updateView(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        Log.d("GetExploresReducer","updateViews $action $state $view")
        if (action == this._action && state != null) {
            store?.router?.route(action)
            state.exploresReposonse?.Object?.Records?.let {
                view.updateExploresCategories(it)
            }

        }
    }

}