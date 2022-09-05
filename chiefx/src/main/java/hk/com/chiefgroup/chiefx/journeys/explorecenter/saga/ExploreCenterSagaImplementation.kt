package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStore
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView

class ExploreCenterSagaImplementation: ExploreCenterSaga<ExploreCenterStore, ExploreCenterAction, ExploreCenterState, ExploreCenterView> {
    override var action: ExploreCenterAction
        get() = TODO("Not yet implemented")
        set(value) {}
    override var store: ExploreCenterStore?
        get() = TODO("Not yet implemented")
        set(value) {}
}