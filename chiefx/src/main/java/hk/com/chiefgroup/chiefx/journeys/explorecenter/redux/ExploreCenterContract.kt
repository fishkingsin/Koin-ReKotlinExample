package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux;

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

public abstract class ExploreCenterRepository: BaseRepository() {
    abstract suspend fun getExplores(): Result<Any>
}


public abstract class  ExploreCenterRouter: BaseRouter() {
    abstract val navigateToResults: Flow<Boolean>
}

public abstract class  ExploreCenterSaga<StoreType, ActionType, StateType, ViewType>:
    BaseSaga<ActionType, StateType, ViewType>() {
    abstract val store: StoreType?
}

public abstract class  ExploreCenterReducer<StoreType, ActionType, StateType, ViewType>:
    BaseReducer<ActionType, StateType, ViewType>() {
    abstract val store: StoreType?
}

public interface ExploreCenterView: BaseView {
    abstract var exploreCenterStore: ExploreCenterStoreImplementation

    abstract fun updateExplores(exploreState: ExploreCenterState)
    abstract fun updateName(name: String)
}