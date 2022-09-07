package hk.com.chiefgroup.chiefx.journeys.explorecenter.saga

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ExploreCenterGetExploresSaga(override val store: ExploreCenterStoreImplementation?): ExploreCenterSagaImplementation(store) {
    private val _action: ExploreCenterBaseAction = GetExplores()
    override var action: ExploreCenterBaseAction
        get() = _action
        set(value) {}

    override suspend fun onDispatch(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: List<ExploreCenterView>?
    ) {
        store?.put(State(State.loading), action)
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            runBlocking {
                val result = store?.repository?.getExplores()

                when {
                    result?.isSuccess == true -> {
                        result.getOrNull()?.let {
                            store?.put(action, it)
                        }
                    }
                    result?.isFailure == true -> {
                        result.exceptionOrNull()?.let { error ->

                            error.message?.let { message ->
                                store?.put(error(message), error)
                            }
                        }
                    }
                }
                store?.put(State(State.loaded), action)
            }
            executor.shutdown()
        }, 2, TimeUnit.SECONDS)

    }
}