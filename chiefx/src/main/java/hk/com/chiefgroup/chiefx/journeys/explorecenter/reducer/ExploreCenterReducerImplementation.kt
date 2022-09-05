package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStore
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

open class ExploreCenterReducerImplementation(override var store: ExploreCenterStoreImplementation?):
    ExploreCenterReducer<ExploreCenterStoreImplementation, ExploreCenterAction, ExploreCenterState, ExploreCenterView>() {
    open var action: ExploreCenterAction
        get() = TODO("Not yet implemented")
        set(value) {}
}