package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState


@Composable
fun ExploreCenterCategoryVerticalListView(records: List<ExploreCategory>, state: ObservableState<ExploreCenterState>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(records) { item ->
            Text(text = item.Name ?:"", modifier = Modifier
                .fillMaxSize()
                .clickable {

                })
            ExploreCenterItemHorizontalListView(item, state)
        }
    }
}