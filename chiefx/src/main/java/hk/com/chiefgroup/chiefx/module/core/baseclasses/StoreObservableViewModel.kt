package hk.com.chiefgroup.chiefx.module.core.baseclasses

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.rekotlin.Store
import org.rekotlin.Subscriber


open class StoreObservableViewModel<T>(var store: Store<T>) : ViewModel(),
    Subscriber<T> {
    companion object {
        private const val TAG = "StoreObservable"
    }
    init {
        store.subscribe(this)

    }

    @SuppressLint("LongLogTag")
    override fun newState(state: T) {
        Log.d(TAG, "newState $state")

    }


}

open class ViewModelFactory<T>(store: Store<T>) :
    ViewModelProvider.Factory {
    private val _store: Store<T>

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StoreObservableViewModel(_store) as T
    }


    init {
        _store = store
    }
}