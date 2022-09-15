package hk.com.chiefgroup.chiefx.module.core.baseclasses

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import org.rekotlin.Action
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.Route
import org.rekotlin.router.RouteSegment
import org.rekotlin.router.SetRouteAction
import javax.inject.Inject

public interface Dispatcher {
    public fun dispatch(action: Action)
    public fun pop()
    public fun push(segment: String)
}

@HiltViewModel
class ObservableState<T: BaseRoutableState> @Inject constructor(private var store: Store<T>) : ViewModel(),
    Subscriber<T>, Dispatcher {
    companion object {
        private const val TAG = "StoreObservable"
    }
    public var current by mutableStateOf(store.state)
    private var _navigateTo: Channel<List<String>> = Channel(Channel.BUFFERED)

    public var navigateTo: Flow<List<String>> = _navigateTo.receiveAsFlow()
    init {
        store.subscribe(this)

    }

    override fun onCleared() {
        super.onCleared()
        store.subscribe(this)
    }

    @SuppressLint("LongLogTag")
    override fun newState(state: T) {
        Log.d(TAG, "newState ${state.navigationState?.route}")
        Log.d(TAG, "newState segment ${state.navigationState?.route?.segments}")
        runBlocking {
            state.navigationState?.route?.segments?.let { segments ->
                Log.d(TAG, "_navigateTo.send(${segments.map { it.id }})")
                _navigateTo.send(segments.map { it.id })
            }
        }
        current = state


    }

    // MARK: Public methods
    public override fun dispatch(action: Action) {
        store.dispatch(action)
    }

    public override fun push(segment: String) {
        current.navigationState?.route?.segments?.toMutableList()?.let {
            var newSegment = it
            newSegment.add(RouteSegment(segment))
            store.dispatch(SetRouteAction(Route(newSegment)))
        }

    }

    public override fun pop() {
        current.navigationState?.route?.segments?.toMutableList()?.let {
            var newSegment = it
            newSegment.removeLast()
            store.dispatch(SetRouteAction(Route(newSegment)))
        }
    }
}


class ObservableStateFactory<R: BaseRoutableState>(store: Store<R>) :
ViewModelProvider.Factory {
    private val _store: Store<R>

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ObservableState(_store) as T
    }


    init {
        _store = store
    }
}