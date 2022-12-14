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
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Devices


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
            Column(Modifier.clickable { onItemClick(item) }) {
                HorizontalCadView(
                    record = item,
                    maxWidth = if (category.ExplorerType == "wide") {
                        300.dp
                    } else {
                        120.dp
                    }
                )
            }
        }
    }
}



@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true, showBackground = true)
@Composable
fun ExploreCenterItemHorizontalListViewPreview() {
    ExploreCenterItemHorizontalListView(category = mockCategory(), onItemClick = {
        println("$it")
    })
}