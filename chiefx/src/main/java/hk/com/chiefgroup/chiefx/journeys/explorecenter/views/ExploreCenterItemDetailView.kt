package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.androidx.compose.get


@Composable
fun ExploreCenterItemDetailView(
    item: ExploreItem?,
    dispatcher: Dispatcher = get<ObservableState<ExploreCenterState>>()
) {
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

                CustomWebView(
                    modifier = Modifier,
                    url = item?.WebsiteUrl ?: "https://google.com",
                    onBack = { },
                    onProgressChange = { },
                    initSettings = {},
                    onReceivedError = {}
                )
            }
        }
    )

}