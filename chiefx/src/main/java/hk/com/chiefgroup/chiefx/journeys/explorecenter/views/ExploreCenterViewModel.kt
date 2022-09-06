package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking


class ExploreCenterViewModel(override var exploreCenterStore: ExploreCenterStoreImplementation): ViewModel(), ExploreCenterView {

    init {
        exploreCenterStore.register(this)
    }

    var records by mutableStateOf(listOf<ExploresRecord>())
    var isLoading by mutableStateOf(false)

    private val _navigateToExploreCenterDetails = Channel<Boolean>(Channel.BUFFERED)
    val navigateToExploreCenterDetails: Flow<Boolean> = _navigateToExploreCenterDetails.receiveAsFlow()

    fun navigateToExploreCenterDetails(action: ExploreCenterAction): Unit = runBlocking {
        _navigateToExploreCenterDetails.send(true)
    }

    override fun updateExplores(exploreState: ExploreCenterState) {
        exploreState.exploresReposonse?.Object?.Records?.let {
            records = it
        }
    }

    override fun updateName(name: String) {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator(type: String) {
        isLoading = false
    }

    override fun showLoadingIndicator(type: String) {
        isLoading = false
    }

    override fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?) {
        TODO("Not yet implemented")
    }

    fun getExplores() {
        exploreCenterStore.dispatch(hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.getExplores())
    }

    override var key: String = "ExploreCenterViewModel"
}

class ExploreCenterViewModelFactory(store: ExploreCenterStoreImplementation) :
    ViewModelProvider.Factory {
    private val _store: ExploreCenterStoreImplementation

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExploreCenterViewModel(_store) as T
    }


    init {
        _store = store
    }
}