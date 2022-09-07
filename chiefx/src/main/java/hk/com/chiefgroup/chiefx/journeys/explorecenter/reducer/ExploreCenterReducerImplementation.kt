package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

open class ExploreCenterReducerImplementation(override var store: ExploreCenterStoreImplementation?):
    ExploreCenterReducer<ExploreCenterStoreImplementation, ExploreCenterBaseAction, ExploreCenterState, ExploreCenterView>() {
    open var action: ExploreCenterBaseAction
        get() = TODO("Not yet implemented")
        set(value) {}
}