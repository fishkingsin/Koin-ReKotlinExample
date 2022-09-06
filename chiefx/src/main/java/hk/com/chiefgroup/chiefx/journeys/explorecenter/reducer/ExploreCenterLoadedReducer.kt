package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.state
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State

class ExploreCenterLoadedReducer(override var store: ExploreCenterStoreImplementation?): ExploreCenterReducerImplementation(store) {
    private val _action: ExploreCenterAction = state(State.loaded)
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
        return state
    }

    override fun updateView(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        view.hideLoadingIndicator(action.toString())
    }
}