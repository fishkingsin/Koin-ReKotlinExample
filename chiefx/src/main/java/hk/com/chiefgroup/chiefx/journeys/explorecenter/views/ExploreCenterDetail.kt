package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem

@Composable
fun ExploreCenterCategoryItemVerticalListView(
    category: ExploreCategory
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        viewModel.records.firstOrNull()?.Records?.firstOrNull()?.let {
//            Text(text = it.Title ?: "")
//        }
        LazyRow {
            items(category.Records) { item ->
                Card {
                    Column(Modifier.padding(10.dp)) {
                        Text(item.Title ?: "", fontWeight = FontWeight.W700)
                        Text(item.Subtitle ?: "")
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestExploreCenterCategoryItemVerticalListViewPreview() {
    MaterialTheme() {
        ExploreCenterCategoryItemVerticalListView(ExploreCategory(Records = arrayListOf(ExploreItem())))
    }
}