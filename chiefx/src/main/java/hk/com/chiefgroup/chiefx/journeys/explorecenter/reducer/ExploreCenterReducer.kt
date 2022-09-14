package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import org.rekotlin.Action
import org.rekotlin.router.NavigationState
import org.rekotlin.router.Route
import org.rekotlin.router.navigationReducer

fun exploreCenterReducer(action: Action, oldState: ExploreCenterState?): ExploreCenterState {
    // if no state has been provided, create the default state
    val state = oldState ?: ExploreCenterState()
    println("action $action")
    return state.copy(
        selectedCategory = exploreCenterSelectedCategoryReducer(action, state.selectedCategory),
        selectedItem = exploreCenterSelectedItemReducer(action, state.selectedItem),
        exploresResponse = exploreCenterRequestExploresReducer(action, state.exploresResponse),
        categories = exploreCenterRequestCategoriesReducer(action, state.categories),
        isLoading = exploreCenterRequestExploresStarted(action, state.isLoading),
        error = exploreCenterRequestExploresFailedReducer(action, state.error),
        navigationState = exploreCenterNavigationReducerReducer(action, state.navigationState)
    )
}
fun exploreCenterNavigationReducerReducer(action: Action, oldState: NavigationState?): NavigationState? {
    val state = oldState ?: NavigationState(Route("Root"))
    return when (action) {
        is ExploreCenterRequestExploresExitWithResult -> {
            state.copy(Route())
        }
        else -> {
            navigationReducer(action, state)
        }
    }
}
fun exploreCenterRequestExploresFailedReducer(action: Action, oldState: Throwable?): Throwable? {
    return when (action) {
        is ExploreCenterRequestExploresFailed -> {
            action.error
        }
        else -> {
            oldState
        }
    }
}

fun exploreCenterRequestExploresStarted(action: Action, oldState: Boolean?): Boolean {
    val state = oldState ?: false
    return when (action) {
        is ExploreCenterRequestExploresStarted -> {
            true
        }
        is ExploreCenterRequestExploresEnded -> {
            false
        }
        else -> {
            state
        }
    }
}

fun exploreCenterRequestExploresReducer(action: Action, oldState: ExploresReposonse?): ExploresReposonse {
    val state = oldState ?: ExploresReposonse()
    return when (action) {
        is ExploreCenterRequestExploresSuccess -> {
            action.response
        }
        else -> {
            state
        }
    }
}

fun exploreCenterRequestCategoriesReducer(action: Action, oldState: ArrayList<ExploreCategory>?): ArrayList<ExploreCategory> {
    val state = oldState ?: ArrayList()
    return when (action) {
        is ExploreCenterRequestExploresSuccess -> {
            action.response.Object?.Records ?: ArrayList()
        }
        else -> {
            state
        }
    }
}

fun exploreCenterSelectedCategoryReducer(action: Action, oldState: ExploreCategory?): ExploreCategory {
    val state = oldState ?: ExploreCategory()
    return when (action) {
         is ExploreCenterSelectedCategory -> {
            action.category
        }
        else -> {
            state
        }
    }

}

fun exploreCenterSelectedItemReducer(action: Action, oldState: ExploreItem?): ExploreItem {
    val state = oldState ?: ExploreItem()
    return when (action) {
        is ExploreCenterSelectedItem -> {
            action.item
        }
        else -> {
            state
        }
    }

}