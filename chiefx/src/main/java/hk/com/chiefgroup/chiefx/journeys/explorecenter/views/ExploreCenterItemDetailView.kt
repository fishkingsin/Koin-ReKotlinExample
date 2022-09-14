package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.activity.compose.BackHandler
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher


@Composable
fun ExploreCenterItemDetailView(item: ExploreItem?, dispatcher: Dispatcher) {
    BackHandler { dispatcher.pop() }
    Text(text = item?.Title ?: "")
}