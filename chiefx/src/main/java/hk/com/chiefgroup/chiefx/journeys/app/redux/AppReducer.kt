package hk.com.chiefgroup.chiefx.journeys.app.redux

import org.rekotlin.Action
import org.rekotlin.router.navigationReducer

fun appReducer(action: Action, oldState: AppState?): AppState {
    val state = oldState ?: AppState()

    return state.copy(
        navigationState = navigationReducer(action, state.navigationState),
    )
}