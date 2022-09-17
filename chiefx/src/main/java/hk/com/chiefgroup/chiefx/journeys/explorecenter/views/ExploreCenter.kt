package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.DismissThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.androidx.compose.getKoin
import org.koin.core.qualifier.named

@Composable
fun ExploreCenter(
    state: ObservableState<ExploreCenterState> = getKoin()
        .getScope(ExploreCenterActivity.scopeId)
        .get(qualifier = named(ExploreCenterActivity.viewModelQualifier))
) {
    LaunchedEffect(key1 = "key", block = {
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
    CircularProgressIndicator()
}

@Composable
fun Root(
    state: ObservableState<ExploreCenterState> = getKoin()
        .getScope(ExploreCenterActivity.scopeId)
        .get(qualifier = named(ExploreCenterActivity.viewModelQualifier))
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explore Center") },
                navigationIcon = {
                    Text(
                        text = "Close",
                        Modifier.clickable { state.dispatch(DismissThunk()) })
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