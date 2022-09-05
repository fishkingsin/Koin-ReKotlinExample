package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux;

import hk.com.chiefgroup.chiefx.module.core.baseclasses.*

public interface ExploreCenterRepository: BaseRepository {
}


public interface ExploreCenterRouter: BaseRouter {

}

public interface ExploreCenterSaga<StoreType, ActionType, StateType, ViewType>: BaseSaga<ActionType, StateType, ViewType> {
    var action: ActionType
    var store: StoreType?
}

public interface ExploreCenterReducer<StoreType, ActionType, StateType, ViewType>: BaseReducer<ActionType, StateType, ViewType> {
    var action: ActionType
    var store: StoreType?
}

public interface ExploreCenterView: BaseView {
    var exploreCenterStore: ExploreCenterStore
}