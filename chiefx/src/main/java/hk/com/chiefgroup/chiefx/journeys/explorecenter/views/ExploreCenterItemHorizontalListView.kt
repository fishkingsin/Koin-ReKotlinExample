package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedCategoryThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction

@Composable
fun ExploreCenterItemHorizontalListView(
    category: ExploreCategory,
    state: ObservableState<ExploreCenterState>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                state.dispatch(
                    ExploreCenterSelectedCategoryThunk(
                        category,
                        state.current.navigationState?.route ?: Route("root")
                    )
                )
            }
    ) {
        items(category.Records) { item ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(horizontal = 4.dp)
                    .clickable { state.dispatch(SetRouteAction(Route("root"))) }) {
                Column() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.BannerUrl ?: "https://picsum.photos/600")
                            .diskCacheKey(item.SeqNo.toString())
                            .crossfade(true)
                            .build(),
                        contentDescription = null
                    )
                    Column() {


                        Text(item.Title ?: "", fontWeight = FontWeight.W700)
                        Text(item.Subtitle ?: "")
                    }
                }

            }
        }
    }
}

@Preview(
    name = "PreviewExploreCenterItemHorizontalListView",
    showSystemUi = true,
    showBackground = true
)
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