package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.widget.ImageView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem

@Composable
fun ExploreCenterItemHorizontalListView(items: List<ExploreItem>) {
    LazyRow {
        items(items) { item ->
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
    ExploreCenterItemHorizontalListView(emptyList())
}