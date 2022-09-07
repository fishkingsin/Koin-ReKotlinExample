package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State

class ExploreCenterLoadedReducer(override var store: ExploreCenterStoreImplementation?): ExploreCenterReducerImplementation(store) {
    private val _action: ExploreCenterBaseAction = ExploreCenterAction.State(State.loaded)
    override var action: ExploreCenterBaseAction
        get() = _action
        set(value) {}

    override fun willUpdateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ): ExploreCenterState? {
        return state
    }

    override fun onUpdate(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        payload: Any?
    ): ExploreCenterState? {
        return state
    }

    override fun updateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        view.hideLoadingIndicator(action.toString())
    }
}