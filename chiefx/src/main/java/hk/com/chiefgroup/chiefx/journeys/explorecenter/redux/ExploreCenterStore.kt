package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import android.util.Log
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducerImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterSagaImplementation
import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseStore
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import java.lang.ref.WeakReference

open class ExploreCenterStore<RepositoryType> : BaseStore<
        ExploreCenterBaseAction,
        ExploreCenterView,
        WeakReference<ExploreCenterView>,
        ExploreCenterState,
        ExploreCenterRouterImplementation,
        RepositoryType,
        HashMap<ExploreCenterBaseAction, ExploreCenterSagaImplementation>,
        HashMap<ExploreCenterBaseAction, ExploreCenterReducerImplementation>,
        ExploreCenterSagaImplementation,
        ExploreCenterReducerImplementation,
        >() {
    companion object {
        private val TAG = "ExploreCenterStore"
    }
    private var _router: ExploreCenterRouterImplementation? = null
    public override val router: ExploreCenterRouterImplementation?
        get() {
            return _router
        }

    internal var _state: MutableStateFlow<ExploreCenterState?> = MutableStateFlow(null)
    public override var state: StateFlow<ExploreCenterState?>
        get() = _state
        set(value) {

        }

    private var _routers: HashMap<String, ExploreCenterRouterImplementation?> = HashMap()
    override var routers: HashMap<String, ExploreCenterRouterImplementation?>
        get() = _routers
        set(value) {}
    private var _views: ArrayList<WeakReference<ExploreCenterView>> = ArrayList()
    override var views: ArrayList<WeakReference<ExploreCenterView>>
        get() = _views
        set(value) { _views = value }
    private var _repository: RepositoryType? = null
    override var repository: RepositoryType?
        get() = _repository
        set(value) { _repository = value }
    private var _sagas: HashMap<ExploreCenterBaseAction, ExploreCenterSagaImplementation> = HashMap()
    override var sagas: HashMap<ExploreCenterBaseAction, ExploreCenterSagaImplementation>
        get() = _sagas
        set(value) { _sagas = value }
    private var _reducers: HashMap<ExploreCenterBaseAction, ExploreCenterReducerImplementation> = HashMap()
    override var reducers: HashMap<ExploreCenterBaseAction, ExploreCenterReducerImplementation>
        get() = _reducers
        set(value) { _reducers = value }


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

    public override fun resetModule(): Unit = runBlocking {
        _state.emit(ExploreCenterState())
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

    open override fun updateViews(action: ExploreCenterBaseAction) {
        Log.d(TAG,"updateViews ------before compact views $views")
        compact()
        views.forEach {
            val view = it.get()
            if (view != null) {
                updateView(action, view)
            }
        }
        Log.d(TAG,"updateViews ------after compact views $views")
    }

    open override fun dispatch(action: ExploreCenterBaseAction): Unit = runBlocking {
        compact()
        views.forEach {
            it.get()?.let { view ->
                val newState = reducers[action]?.willUpdateView(action, state.value, view)
                _state.emit(newState)
            }
        }
        sagas.keys.first { it.key == action.key }?.let { selfAction ->


            sagas[selfAction]?.onDispatch(
                action,
                state.value,
                _views.mapNotNull { view ->
                    return@mapNotNull view.get()
                }
            )

            Log.d(TAG, "${sagas[action]}")
        }

    }

    open override fun updateView(action: ExploreCenterBaseAction, view: ExploreCenterView) {
        reducers.keys.first { it.key == action.key }?.let { selfAction ->
            reducers[selfAction]?.updateView(action, state.value, view)
        }
    }

    open override fun viewDidLoad(view: ExploreCenterView) {
        reducers.keys.forEach { reducers[it]?.updateView(it, state.value, view) }
    }

    open override fun viewDidAppear(view: ExploreCenterView) {
        updateView(State(State.viewDidAppear), view)
    }

    open override fun viewWillAppear(view: ExploreCenterView) {
        updateView(State(State.viewWillAppear), view)
    }

    open override fun viewDeinit(view: ExploreCenterView) {
        Log.d(TAG,"-------------remove $view)-------------")
        updateView(State(State.viewDeinit), view)
        compact()
        if (views.isEmpty()) {
            dispose()
        } else {
            Log.d(TAG,"-------------\n$this views ${views.size}\n-------------")
        }
    }

    open override fun dispose() {
        resetModule()
        
        Log.d(TAG,"-------------\n$this views ${views.size}\n-------------")
        views.clear()
        sagas.clear()
        reducers.clear()
        repository = null
        Log.d(TAG,"-------------\n$this sagas ${sagas.size}\n-------------")
        Log.d(TAG,"-------------\n$this reducers ${reducers.size}\n-------------")
    }

    // Worker saga will be fired on USER_FETCH_REQUESTED actions
    public override fun put(action: ExploreCenterBaseAction, payload: Any?): Unit= runBlocking {
        reducers.keys.first { it.key == action.key }?.let { selfAction ->
            val newState = reducers[selfAction]?.onUpdate(action, state.value, payload)
            _state.emit(newState)
            updateViews(action)
            Log.d(TAG, "put(ActionType: $action, Any: $payload)")
        }
    }
}