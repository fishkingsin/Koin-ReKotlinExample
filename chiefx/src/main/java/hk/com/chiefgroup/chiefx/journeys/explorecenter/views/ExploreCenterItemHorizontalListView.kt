package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedCategoryThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Devices
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.Store
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction
import org.rekotlin.store


@Composable
fun ExploreCenterItemHorizontalListView(
    category: ExploreCategory,
    state: ObservableState<ExploreCenterState>,
    maxHeight: Float = 320.0f
) {
    LazyRow(
        modifier = Modifier
            .fillMaxHeight(maxHeight)
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
            HorizontalCadView(record = item, state, maxWidth = if (category.ExplorerType == "wide") {300.dp} else { 120.dp } )
        }
    }
}



@Preview(device = Devices.PIXEL_2_XL)
@Composable
fun ExploreCenterItemHorizontalListViewPreview() {
    ExploreCenterItemHorizontalListView(category = ExploreCategory(), state = ObservableState<ExploreCenterState>(store<ExploreCenterState>(
        ::ExploreCenterReducer, null)))
}