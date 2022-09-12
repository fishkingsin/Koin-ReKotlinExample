package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.store

@Composable
fun ExploreCenterCategoryItemVerticalListView(
    category: ExploreCategory,
    state: ObservableState<ExploreCenterState>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        items(category.Records) { item ->
            HorizontalCadView(record = item, state = state)
        }

    }
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
            state = ObservableState(store(::ExploreCenterReducer, null))
        )
    }
}