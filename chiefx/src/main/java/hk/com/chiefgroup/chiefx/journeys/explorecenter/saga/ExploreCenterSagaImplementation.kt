package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStore
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

open class ExploreCenterSagaImplementation(override val store: ExploreCenterStoreImplementation?): ExploreCenterSaga<ExploreCenterStoreImplementation, ExploreCenterAction, ExploreCenterState, ExploreCenterView>() {
    open var action: ExploreCenterAction
        get() = TODO("Not yet implemented")
        set(value) {}
}