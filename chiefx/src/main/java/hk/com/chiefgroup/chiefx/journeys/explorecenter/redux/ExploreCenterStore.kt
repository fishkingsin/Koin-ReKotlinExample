package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.none
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.state
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducerImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterSagaImplementation
import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseStore
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State
import java.lang.ref.WeakReference

open class ExploreCenterStore<RepositoryType> : BaseStore<
        ExploreCenterAction,
        ExploreCenterView,
        WeakReference<ExploreCenterView>,
        ExploreCenterState,
        ExploreCenterRouter,
        RepositoryType,
        HashMap<ExploreCenterAction, ExploreCenterSagaImplementation>,
        HashMap<ExploreCenterAction, ExploreCenterReducerImplementation>,
        ExploreCenterSagaImplementation,
        ExploreCenterReducerImplementation,
        >() {
    private var _state: ExploreCenterState? = null
    override var state: ExploreCenterState?
        get() = _state
        set(value) { _state = value }
    override var routers: HashMap<String, ExploreCenterRouter?>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var router: ExploreCenterRouter?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var views: ArrayList<WeakReference<ExploreCenterView>>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var repository: RepositoryType?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var sagas: HashMap<ExploreCenterAction, ExploreCenterSagaImplementation>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var reducers: HashMap<ExploreCenterAction, ExploreCenterReducerImplementation>
        get() = TODO("Not yet implemented")
        set(value) {}


    open override fun registerRepository(repository: RepositoryType) {
        this.repository = repository
    }

    open override fun registerSaga(saga: ExploreCenterSagaImplementation) {
        sagas[saga.action] = saga
    }

    open override fun unregisterSaga(saga: ExploreCenterSagaImplementation) {
        sagas.remove(saga.action)
    }

    open override fun registerSagas(sagas: List<ExploreCenterSagaImplementation>) {
        sagas.forEach {
            this.sagas[it.action] = it
        }
    }

    open override fun unregisterSagas(sagas: List<ExploreCenterSagaImplementation>) {
        sagas.forEach {
            this.sagas.remove(it.action)

        }
    }

    open override fun registerReducer(reducer: ExploreCenterReducerImplementation) {
        reducers[reducer.action] = reducer
    }

    open override fun unregisterReducer(reducer: ExploreCenterReducerImplementation) {
        reducers.remove(reducer.action)
    }

    open override fun registerReducers(reducers: List<ExploreCenterReducerImplementation>) {
        reducers.forEach {
            this.reducers[it.action] = it
        }
    }

    open override fun unregisterReducers(reducers: List<ExploreCenterReducerImplementation>) {
        reducers.forEach {
            this.reducers.remove(it.action)
        }
    }

    public override fun resetModule() {
        state = ExploreCenterState()
    }

    public override fun compact() {
        views.removeAll { it.get() == null }
    }

    open override fun register(view: ExploreCenterView) {
        views.add(WeakReference<ExploreCenterView>(view))
    }

    open override fun unregister(view: ExploreCenterView) {
        views.removeAll {
            view.key == it.get()?.key
        }
    }

    open override fun updateViews(action: ExploreCenterAction) {
//        debugLog("------before compact views \(views)")
        compact()
        views.forEach {
            val view = it.get()
            if (view!=null) {
                updateView(action, view)
            }
        }
//        debugLog("------after compact views \(views)")
    }

    open override fun dispatch(action: ExploreCenterAction) {
        compact()
//        debugLog("\(String(describing: sagas[action]))")
        
    }

    open override fun updateView(action: ExploreCenterAction, view: ExploreCenterView) {
        reducers[action]?.updateView(action, state, view)
    }

    open override fun viewDidLoad(view: ExploreCenterView) {
        updateView(none(), view)
        reducers.keys.forEach { reducers[it]?.updateView(it, state, view) }
    }

    open override fun viewDidAppear(view: ExploreCenterView) {
//        updateView(state(State.viewDidAppear), view)
    }

    open override fun viewWillAppear(view: ExploreCenterView) {
        updateView(state(State.viewWillAppear), view)
    }

    open override fun viewDeinit(view: ExploreCenterView) {
//        debugLog("-------------\(#funtion) remove \(String(describing: view))-------------")
        updateView(state(State.viewDeinit), view)
        compact()
        if (views.isEmpty()) {
            dispose()
        } else {

        }
    }

    open override fun dispose() {
        resetModule()
//        debugLog("-------------\(#file) \(#funtion)-------------")
//        debugLog("-------------\n\(String(describing: self)) views \(views.count)\n-------------")
        views.clear()
        sagas.clear()
        reducers.clear()
        repository = null
//        debugLog("-------------\n\(String(describing: self)) sagas \(sagas.count)\n-------------")
//        debugLog("-------------\n\(String(describing: self)) reducers \(reducers.count)\n-------------")
    }

    // Worker saga will be fired on USER_FETCH_REQUESTED actions
    public override fun put(action: ExploreCenterAction, payload: Any?) {
        reducers[action]?.onUpdate(action, state, payload)
        updateViews(action)
//        debugLog("put(ActionType: \(action), Any: \(payload))")
//        Task {
//            await _threadSafeExecute {
//                state = reducers[action]?.onUpdate(action, state: state, payload: payload)
//                updateViews(action)
//            }
//        }
    }
}