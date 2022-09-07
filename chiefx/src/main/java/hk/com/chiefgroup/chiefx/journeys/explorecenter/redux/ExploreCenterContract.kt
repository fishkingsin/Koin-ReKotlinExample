package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux;

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import hk.com.chiefgroup.chiefx.module.core.baseclasses.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

public abstract class ExploreCenterRepository: BaseRepository() {
    abstract suspend fun getExplores(): Result<ExploresReposonse>
}


public abstract class  ExploreCenterRouter<ActionType>: BaseRouter() {
    abstract fun route(action: ActionType)
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
    abstract fun updateExploresCategories(categories: List<ExploreCategory>)
    abstract fun updateExplores(exploreItem: List<ExploreItem>)
    abstract fun updateSelectedCategory(category: ExploreCategory)
}