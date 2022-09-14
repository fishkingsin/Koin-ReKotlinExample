package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedCategoryThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedItemThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Devices
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.store


@Composable
fun ExploreCenterItemHorizontalListView(
    category: ExploreCategory,
    maxHeight: Float = 320.0f,
    onItemClick: (ExploreItem) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxHeight(maxHeight)
    ) {

        items(category.Records) { item ->
            HorizontalCadView(
                record = item,
                maxWidth = if (category.ExplorerType == "wide") {300.dp} else { 120.dp },
                clickable = { onItemClick(item) }
            )
        }
    }
}



@Preview(device = Devices.PIXEL_2_XL)
@Composable
fun ExploreCenterItemHorizontalListViewPreview() {
    ExploreCenterItemHorizontalListView(category = ExploreCategory(), onItemClick = {

    })
}