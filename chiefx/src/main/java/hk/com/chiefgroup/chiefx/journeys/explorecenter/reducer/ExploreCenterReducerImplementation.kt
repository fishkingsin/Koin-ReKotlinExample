package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRouterImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

open class ExploreCenterReducerImplementation(
    override var store: ExploreCenterStoreImplementation?,
    override var router: ExploreCenterRouterImplementation? = null
):
    ExploreCenterReducer<ExploreCenterStoreImplementation, ExploreCenterBaseAction, ExploreCenterState, ExploreCenterView, ExploreCenterRouterImplementation>() {

    open var action: ExploreCenterBaseAction
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun updateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        TODO("Not yet implemented")
    }

    override fun willUpdateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ): ExploreCenterState? {
        TODO("Not yet implemented")
    }

    override fun onUpdate(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        payload: Any?
    ): ExploreCenterState? {
        TODO("Not yet implemented")
    }
}