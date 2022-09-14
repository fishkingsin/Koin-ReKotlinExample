package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedItemThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.dismissThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction
import org.rekotlin.store

@Composable
fun ExploreCenterCategoryItemVerticalListView(
    category: ExploreCategory,
    dispatcher: Dispatcher
) {
    BackHandler { dispatcher.pop() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explore Center ${category.Name}") },
                navigationIcon = {
                    Text(text = "Back", Modifier.clickable {
                        dispatcher.pop()
                    })
                }
            )
        },
        content = { padding ->


            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                items(category.Records) { item ->
                    HorizontalCadView(
                        record = item,
                        maxWidth = 320.dp,
                        clickable = {
                            dispatcher.dispatch(ExploreCenterSelectedItemThunk(item, dispatcher))
                        })
                    Spacer(modifier = Modifier.padding(8.dp))
                }


            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestExploreCenterCategoryItemVerticalListViewPreview() {
    MaterialTheme() {
        ExploreCenterCategoryItemVerticalListView(
            category = ExploreCategory(
                Records = arrayListOf(
                    ExploreItem(
                        Title = "Title",
                        Subtitle = "Subtitle",
                        BannerUrl = "https://picsum.photos/600"
                    ),
                    ExploreItem(
                        Title = "Title",
                        Subtitle = "Subtitle",
                        BannerUrl = "https://picsum.photos/600"
                    ),
                    ExploreItem(
                        Title = "Title",
                        Subtitle = "Subtitle",
                        BannerUrl = "https://picsum.photos/600"
                    ),
                    ExploreItem(
                        Title = "Title",
                        Subtitle = "Subtitle",
                        BannerUrl = "https://picsum.photos/600"
                    )
                )
            ),
            dispatcher = ObservableState(store(::exploreCenterReducer, null))
        )
    }
}