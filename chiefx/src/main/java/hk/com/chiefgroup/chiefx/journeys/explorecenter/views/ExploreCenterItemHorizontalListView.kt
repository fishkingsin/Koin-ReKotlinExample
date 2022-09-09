package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.sp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import androidx.compose.ui.text.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel.ExploreCenterStateObservableStateViewModel
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateViewModel

@Composable
fun ExploreCenterItemHorizontalListView(category: ExploreCategory, viewModel: ObservableStateViewModel<ExploreCenterState>) {
    LazyRow (
        modifier = Modifier
            .fillMaxSize()
            .clickable { viewModel.dispatch(ExploreCenterSelectedCategory(category)) }
    ){
        items(category.Records) { item ->
            Card(modifier = Modifier
                .fillMaxSize()) {
                Column(Modifier.padding(10.dp)) {
                    Text(item.Title ?: "", fontWeight = FontWeight.W700)
                    Text(item.Subtitle ?: "")
                }
            }
        }
    }
}

@Preview(name = "PreviewExploreCenterItemHorizontalListView", showSystemUi = true, showBackground = true)
@Composable
fun PreviewExploreCenterItemHorizontalListView() {
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
    LazyRow(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            Log.d("COMPOSE", "This get rendered $item")
            when (item) {
                "A" -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Do Nothing
                }
                "D" -> {
                    Text(text = item)
                }
                else -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
            }
        })
    }
//    ExploreCenterItemHorizontalListView(ExploreCategory(), ExploreCenterViewModel(
//        ExploreCenterStoreImplementation()
//    ))
}