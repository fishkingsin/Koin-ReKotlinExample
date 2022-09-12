package hk.com.chiefgroup.chiefx.module.core.baseclasses

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.rekotlin.Action
import org.rekotlin.Store
import org.rekotlin.Subscriber


open class ObservableState<T>(var store: Store<T>) : ViewModel(),
    Subscriber<T> {
    companion object {
        private const val TAG = "StoreObservable"
    }
    public var current by mutableStateOf(store.state)
    init {
        store.subscribe(this)

    }

    override fun onCleared() {
        super.onCleared()
        store.subscribe(this)
    }

    @SuppressLint("LongLogTag")
    override fun newState(state: T) {
        Log.d(TAG, "newState $state")
        current = state

    }

    // MARK: Public methods
    public fun dispatch(action: Action) {
        store.dispatch(action)
    }
}