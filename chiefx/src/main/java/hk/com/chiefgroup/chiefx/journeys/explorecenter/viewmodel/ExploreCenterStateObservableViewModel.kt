package hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.StoreObservableViewModel
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ViewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import org.rekotlin.Store


class ExploreCenterViewModelFactory(store: Store<ExploreCenterState>) :
    ViewModelFactory<ExploreCenterState>(store) {
    private val _store: Store<ExploreCenterState>

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExploreCenterStateObservableViewModel(_store) as T
    }


    init {
        _store = store
    }
}

class ExploreCenterStateObservableViewModel(store: Store<ExploreCenterState>) :
    StoreObservableViewModel<ExploreCenterState>(
        store
    ), ExploreCenterView {

    var categories by mutableStateOf(listOf<ExploreCategory>())
    var isLoading by mutableStateOf(false)
    var selectedCategory by mutableStateOf(ExploreCategory())

    init {

        getExplores()
    }

    private val _navigateToCategoryDetails = Channel<Boolean>(Channel.BUFFERED)

    val navigateToExploreCenterDetails: Flow<Boolean> = _navigateToCategoryDetails.receiveAsFlow()

    override fun updateExploresCategories(categories: ArrayList<ExploreCategory>) {
        this.categories = categories
    }

    override fun updateExplores(exploreItem: ArrayList<ExploreItem>) {
        /* Empty implementation */
    }

    override fun updateSelectedCategory(category: ExploreCategory) {
        selectedCategory = category
    }

    override fun hideLoadingIndicator(type: String) {
        isLoading = false
    }

    override fun showLoadingIndicator(type: String) {
        isLoading = true
    }

    override fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?) {
        TODO("Not yet implemented")
    }

    override var key: String = "ExploreCenterStateObservableViewModel"

    fun selectCategories(category: ExploreCategory): Unit = runBlocking {
        store.dispatch(ExploreCenterSelectedCategory(category))
        _navigateToCategoryDetails.send(true)
    }

    fun getExplores() {
        store.dispatch(GetExploresThunk(ExploreCenterRepositoryImplementation()))
    }

    override fun newState(state: ExploreCenterState) {
        super.newState(state)
        state.isLoading?.let {
            if (it) {
                showLoadingIndicator("")
            } else {
                hideLoadingIndicator("")
            }
        }
        state.exploresResponse?.Object?.Records?.let {
            updateExploresCategories(it)
        }
    }

}