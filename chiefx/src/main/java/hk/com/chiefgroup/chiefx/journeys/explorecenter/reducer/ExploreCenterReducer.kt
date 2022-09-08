package hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import org.rekotlin.Action
import org.rekotlin.router.navigationReducer

fun ExploreCenterReducer(action: Action, oldState: ExploreCenterState?): ExploreCenterState {
    // if no state has been provided, create the default state
    val newState = oldState ?: ExploreCenterState()

    return when(action){

        is ExploreCenterSelectedCategory -> {
            newState.copy(selectedCategory = action.category)
        }
        is ExploreCenterRequestExplores -> {
            return newState
        }
        is ExploreCenterRequestExploresSuccess -> {
            newState.copy(exploresResponse = action.response, categories = action.response.Object?.Records )

        }
        is ExploreCenterRequestExploresStarted -> {
            newState.copy(isLoading = true)

        }
        is ExploreCenterRequestExploresEnded -> {
            newState.copy(isLoading = false)

        }
        is ExploreCenterRequestExploresFailed -> {
            newState.copy(error = action.error)

        }
        else -> {
            println("action $action")
            // https://rakutentech.github.io/ReKotlin/docs/rekotlin-router/
//            return newState.copy(navigationState = navigationReducer(action, newState.navigationState))
            return newState
        }
    }
}