package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

open class ExploreCenterSagaImplementation(override val store: ExploreCenterStoreImplementation?): ExploreCenterSaga<ExploreCenterStoreImplementation, ExploreCenterBaseAction, ExploreCenterState, ExploreCenterView>() {
    open var action: ExploreCenterBaseAction
        get() = TODO("Not yet implemented")
        set(value) {}
}