package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory


@Composable
fun ExploreCenterCategoryVerticleListView(records: List<ExploreCategory>, navController: NavController) {
    LazyColumn {
        items(records) { item ->  
            ExploreCenterItemHorizontalListView(items = item.Records, navController)
        }
    }
}