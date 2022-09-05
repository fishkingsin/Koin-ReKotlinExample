package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import android.util.Log
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
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
        return state
    }

    override fun onUpdate(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        payload: Any?
    ): ExploreCenterState? {
        var newState: ExploreCenterState = state ?: ExploreCenterState("Hello")
        return newState
    }

    override fun updateView(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        Log.d("GetExploresReducer","updateViews $action $state $view")
        if (action == this._action && state != null) {
            view.updateName(state.name)
        }
    }

}