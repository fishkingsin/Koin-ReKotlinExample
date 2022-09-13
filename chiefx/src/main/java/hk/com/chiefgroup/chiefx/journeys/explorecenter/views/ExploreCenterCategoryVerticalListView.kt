package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedCategoryThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.router.Route


@Composable
fun ExploreCenterCategoryVerticalListView(categories: List<ExploreCategory>, state: ObservableState<ExploreCenterState>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(categories) { category ->
            Text(text = category.Name ?:"", modifier = Modifier
                .fillMaxSize()
                .clickable {
                    state.dispatch(ExploreCenterSelectedCategoryThunk(category, state.current.navigationState?.route ?: Route("Root")))
                })
            ExploreCenterItemHorizontalListView(category, state)
        }
    }
}