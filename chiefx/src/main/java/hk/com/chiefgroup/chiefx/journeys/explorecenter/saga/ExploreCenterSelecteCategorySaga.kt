package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ExploreCenterSelecteCategorySaga(override val store: ExploreCenterStoreImplementation?): ExploreCenterSagaImplementation(store) {
    private val _action: ExploreCenterBaseAction = ExploreCenterAction.SelectedCategory()
    override var action: ExploreCenterBaseAction
        get() = _action
        set(value) {}

    override suspend fun onDispatch(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: List<ExploreCenterView>?
    ) {
        store?.put(ExploreCenterAction.State(State.loading), action)
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            runBlocking {
                if (action is ExploreCenterAction.SelectedCategory)
                store?.put(ExploreCenterAction.State(State.loaded), action)
            }
            executor.shutdown()
        }, 2, TimeUnit.SECONDS)

    }
}