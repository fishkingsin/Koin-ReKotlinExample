package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory


@Composable
fun ExploreCenterCategoryVerticalListView(records: List<ExploreCategory>, viewModel: ExploreCenterViewModel) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ) {

        items(records) { item ->  
            ExploreCenterItemHorizontalListView(item, viewModel)
        }
    }
}