package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation

@Composable
fun ExploreCenterItemHorizontalListView(category: ExploreCategory, viewModel: ExploreCenterViewModel) {
    LazyRow (
        modifier = Modifier
            .fillMaxSize()
            .clickable { viewModel.selectCategories(category) }
    ){
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

@Preview
@Composable
fun PreviewExploreCenterItemHorizontalListView() {
    ExploreCenterItemHorizontalListView(ExploreCategory(), ExploreCenterViewModel(
        ExploreCenterStoreImplementation()
    ))
}