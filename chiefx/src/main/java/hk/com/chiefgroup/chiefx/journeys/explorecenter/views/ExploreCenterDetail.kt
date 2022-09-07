package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.selectedCategory

@Composable
fun ExploreCenterCategoryItemVerticalListView(
    category: ExploreCategory
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn {
            items(category.Records) { item ->
                Text(text = item.Title ?: "")
            }
        
            
        }
    }
}

@Preview
@Composable
fun TestExploreCenterCategoryItemVerticalListViewPreview() {
    MaterialTheme() {
        ExploreCenterCategoryItemVerticalListView(ExploreCategory(Records = arrayListOf(ExploreItem(Title = "Title"))))
    }
}