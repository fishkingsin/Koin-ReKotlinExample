package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import org.rekotlin.Action

fun ExploreCenterReducer(action: Action, oldState: ExploreCenterState?): ExploreCenterState {
    // if no state has been provided, create the default state
    val state = oldState ?: ExploreCenterState()
    println("action $action")
    return state.copy(
        selectedCategory = ExploreCenterSelectedCategoryReducer(action, state.selectedCategory),
        exploresResponse = ExploreCenterRequestExploresReducer(action, state.exploresResponse),
        categories = ExploreCenterRequestCategoriesReducer(action, state.categories),
        isLoading = ExploreCenterRequestExploresStarted(action, state.isLoading),
        error = ExploreCenterRequestExploresFailedReducer(action, state.error)
    )
    /*
    return when(action) {
        else -> {
            println("action $action")
            // https://rakutentech.github.io/ReKotlin/docs/rekotlin-router/
//            return newState.copy(navigationState = navigationReducer(action, newState.navigationState))
            return state
        }
    }*/
}

fun ExploreCenterRequestExploresFailedReducer(action: Action, oldState: Throwable?): Throwable? {
    return when (action) {
        is ExploreCenterRequestExploresFailed -> {
            action.error
        }
        else -> {
            oldState
        }
    }
}

fun ExploreCenterRequestExploresStarted(action: Action, oldState: Boolean?): Boolean {
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

fun ExploreCenterRequestExploresReducer(action: Action, oldState: ExploresReposonse?): ExploresReposonse {
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

fun ExploreCenterRequestCategoriesReducer(action: Action, oldState: ArrayList<ExploreCategory>?): ArrayList<ExploreCategory> {
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

fun ExploreCenterSelectedCategoryReducer(action: Action, oldState: ExploreCategory?): ExploreCategory {
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