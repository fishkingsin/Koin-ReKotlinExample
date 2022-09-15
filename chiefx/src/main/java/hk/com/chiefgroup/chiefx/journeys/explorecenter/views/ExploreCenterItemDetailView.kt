package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher


@Composable
fun ExploreCenterItemDetailView(item: ExploreItem?, dispatcher: Dispatcher) {
    BackHandler { dispatcher.pop() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${item?.Title}") },
                navigationIcon = {
                    Text(text = "Back", Modifier.clickable {
                        dispatcher.pop()
                    })
                }
            )
        },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                Text(text = item?.Title ?: "")
            }
        }
    )

}