package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation

@Composable
fun ExploreCenterDetail(
    records: List<ExploreItem>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn {
            items(records) { item ->
                Text(text = item.Title ?: "")
            }
        
            
        }
    }
}

@Preview
@Composable
fun TestExploreCenterDetailPreview() {
    MaterialTheme() {
        ExploreCenterDetail(records = listOf(ExploreItem(Title = "Title")))
    }
}