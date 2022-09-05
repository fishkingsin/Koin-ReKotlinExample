package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.getExplores
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ExploreCenterGetExploresSaga(override val store: ExploreCenterStoreImplementation?): ExploreCenterSagaImplementation(store) {
    private val _action: ExploreCenterAction = getExplores()
    override var action: ExploreCenterAction
        get() = _action
        set(value) {}

    override suspend fun onDispatch(
        action: ExploreCenterAction,
        state: ExploreCenterState?,
        view: List<ExploreCenterView>?
    ) {
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            runBlocking {
                val result = store?.repository?.getExplores()
                store?.put(action, result)
            }
            executor.shutdown()
        }, 2, TimeUnit.SECONDS)

    }
}