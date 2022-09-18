package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.DismissThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.androidx.compose.getKoin
import org.koin.core.qualifier.named
import org.rekotlin.Store
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction

@Composable
fun ExploreCenter(
    state: ObservableState<ExploreCenterState> = getKoin()
        .getScope(ExploreCenterActivity.scopeId)
        .get(qualifier = named(ExploreCenterActivity.viewModelQualifier))
) {
    LaunchedEffect(key1 = "onLaunch", block = {
        state.dispatch(GetExploresThunk())
    })
    when (state.current.navigationState?.route.toString()) {
        "Root" -> {
            Root(state)
        }
        "Root/Category" -> {
            ExploreCenterCategoryItemVerticalListView(
                state.current.selectedCategory ?: ExploreCategory()
            )
        }
        "Root/Category/Detail", "Root/Detail" -> {
            ExploreCenterItemDetailView(
                state.current.selectedItem
            )
        }
    }

}

@Composable
fun Root(
    state: ObservableState<ExploreCenterState> = getKoin()
        .getScope(ExploreCenterActivity.scopeId)
        .get(qualifier = named(ExploreCenterActivity.viewModelQualifier)),
    appStore: Store<AppState>? = getKoin().get()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explore Center") },
                navigationIcon = {
                    Text(
                        text = "Close",
                        Modifier.clickable {
                            state.dispatch(DismissThunk())
                            appStore?.dispatch(SetRouteAction(Route("App")))
                        })
                }
            )
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if (state.current.isLoading == true) {
                    CircularProgressIndicator()
                } else {

                    ExploreCenterCategoriesView(
                        state.current.categories ?: emptyList(),
                    )

                }
            }
        }
    )
}