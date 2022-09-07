package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import android.util.Log
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

class ExploreCenterGetExploresReducer(override var store: ExploreCenterStoreImplementation?): ExploreCenterReducerImplementation(store) {
    private val _action: ExploreCenterBaseAction = ExploreCenterAction.GetExplores()
    override var action: ExploreCenterBaseAction
        get() = _action
        set(value) {}

    override fun willUpdateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ): ExploreCenterState? {
        var newState: ExploreCenterState = state ?: ExploreCenterState()
        newState.isLoading = true
        return newState
    }

    override fun onUpdate(
        action: ExploreCenterBaseAction,
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
        action: ExploreCenterBaseAction,
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