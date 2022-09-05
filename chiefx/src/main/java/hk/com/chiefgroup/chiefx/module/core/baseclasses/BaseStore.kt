package hk.com.chiefgroup.chiefx.module.core.baseclasses

import java.lang.ref.WeakReference

abstract class BaseStore<
        ActionType,
        ViewReferenceType,
        ViewType: WeakReference<ViewReferenceType>,
        StateType,
        RouterType,
        RepositoryType,
        SagaMapType: HashMap<ActionType, SagaType>,
        ReducerMapType: HashMap<ActionType, ReducerType>,
        SagaType,
        ReducerType,
        > {
    internal abstract var state: StateType?
    internal abstract var routers: HashMap<String, RouterType?>
    internal abstract var views: ArrayList<ViewType>
    internal abstract var repository: RepositoryType?
    internal abstract var sagas: SagaMapType
    internal abstract var reducers: ReducerMapType

    open fun registerRepository(repository: RepositoryType) {
        this.repository = repository
    }
    open fun registerSaga(saga: SagaType) {}
    open fun unregisterSaga(saga: SagaType) {}
    open fun registerSagas(sagas: List<SagaType>) {}
    open fun unregisterSagas(sagas: List<SagaType>) {}
    open fun registerReducer(reducer: ReducerType) {}
    open fun unregisterReducer(reducer: ReducerType) {}
    open fun registerReducers(reducers: List<ReducerType>) {}
    open fun unregisterReducers(reducers: List<ReducerType>) {}
    open fun resetModule() {}
    open fun compact() {}
    open fun register(view: ViewReferenceType) {}
    open fun unregister(view: ViewReferenceType) {}
    open fun updateViews(action: ActionType) {}
    open fun dispatch(action: ActionType) {}
    open fun updateView(action: ActionType, view: ViewReferenceType) {}
    open fun viewDidLoad(view: ViewReferenceType) {}
    open fun viewDidAppear(view: ViewReferenceType) {}
    open fun viewWillAppear(view: ViewReferenceType) {}
    open fun viewDeinit(view: ViewReferenceType) {}
    open fun dispose() {}
    open fun put(action: ActionType, payload: Any?) {}
}