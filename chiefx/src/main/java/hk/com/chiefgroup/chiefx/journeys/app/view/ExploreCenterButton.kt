package hk.com.chiefgroup.chiefx.journeys.app.view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterFragment
import org.koin.androidx.compose.getViewModel
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction

@Composable
fun ExploreCenterActivityButton(
    state: ObservableState<AppState> = getViewModel()
) {
    LaunchedEffect(key1 = "ExploreCenterActivityButtonLaunched", block = {
        println("Store $state")
    })
    val context = LocalContext.current
    Button(onClick = {
        state.dispatch(SetRouteAction(Route("App", "ExploreCenterActivity")))
    }) {
        Text(text = "Explore Center Activity")
    }
}

@Composable
fun ExploreCenterComposeButton(
    state: ObservableState<AppState> = getViewModel()
) {
    LaunchedEffect(key1 = "ExploreCenterComposeButtonLaunched", block = {
        println("Store $state")
    })
    val context = LocalContext.current
    Button(onClick = {
        state.dispatch(SetRouteAction(Route("App", "ExploreCenterView")))
    }) {
        Text(text = "Explore Center View")
    }
}

@Composable
fun ExploreCenterFragmentButton(
    state: ObservableState<AppState> = getViewModel()
) {
    LaunchedEffect(key1 = "ExploreCenterFragmentButtonLaunched", block = {
        println("Store $state")
    })
    val context = LocalContext.current
    Button(onClick = {
        state.dispatch(SetRouteAction(Route("App", "ExploreCenterFragment")))
    }) {
        Text(text = "Explore Center Fragment")
    }
}
