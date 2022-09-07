package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import kotlinx.coroutines.flow.Flow


class ExploreCenterViewModel(override var exploreCenterStore: ExploreCenterStoreImplementation): ViewModel(), ExploreCenterView {

    init {
        exploreCenterStore.register(this)
    }

    var categories by mutableStateOf(listOf<ExploreCategory>())
    var isLoading by mutableStateOf(false)
    var selectedCategory by mutableStateOf(ExploreCategory())

    val navigateToExploreCenterDetails: Flow<Boolean> = exploreCenterStore.router!!.navigateToCategoryDetails

    override fun updateExploresCategories(categories: List<ExploreCategory>) {
        this.categories = categories
    }

    override fun updateExplores(exploreItem: List<ExploreItem>) {
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

    fun selectCategories(category: ExploreCategory) {
        exploreCenterStore.dispatch(SelectedCategory(category))
        exploreCenterStore.router?.route(SelectedCategory(category))
    }

    fun getExplores() {
        exploreCenterStore.dispatch(GetExplores(0))
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