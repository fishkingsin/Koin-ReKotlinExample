package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.dismissThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState


@Composable
fun ExploreCenter(
    state: ObservableState<ExploreCenterState>
) {
    when (state.current.navigationState?.route.toString()) {
        "Root" -> {
            Root(state)
        }
        "Root/Category" -> {
            ExploreCenterCategoryItemVerticalListView(
                state.current.selectedCategory ?: ExploreCategory(),
                state
            )
        }
        "Root/Category/Detail", "Root/Detail" -> {
            ExploreCenterItemDetailView(
                state.current.selectedItem,
                state
            )
        }
    }
}

@Composable
fun Root(state: ObservableState<ExploreCenterState>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Explore Center")},
                navigationIcon = {Text(text = "Close", Modifier.clickable { state.dispatch(dismissThunk()) })}
            )},
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
                        state
                    )

                }
            }
        }
    )
}