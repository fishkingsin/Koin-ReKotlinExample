package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterBaseAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRouterImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterView
import hk.com.chiefgroup.chiefx.module.core.baseclasses.State

class ExploreCenterSelectCategoryReducer(override var store: ExploreCenterStoreImplementation?,
                                         override var router: ExploreCenterRouterImplementation? = null): ExploreCenterReducerImplementation(store) {

    private val _action: ExploreCenterBaseAction = ExploreCenterAction.SelectedCategory()
    override var action: ExploreCenterBaseAction
        get() = _action
        set(value) {}

    override fun willUpdateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ): ExploreCenterState? {
        return state ?: ExploreCenterState()
    }

    override fun onUpdate(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        payload: Any?
    ): ExploreCenterState? {
        val newState = state ?: ExploreCenterState()
        if (payload is ExploreCategory) {
            newState.selectedCategory = payload
            return newState
        }
        return state
    }

    override fun updateView(
        action: ExploreCenterBaseAction,
        state: ExploreCenterState?,
        view: ExploreCenterView
    ) {
        if (action is ExploreCenterAction.SelectedCategory) {
            action.category?.let { view.updateSelectedCategory(it) }
            router?.route(ExploreCenterAction.NavigateToCategoryDetails(action.category))
        }

    }
}