package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.router.Route
import org.rekotlin.router.SetRouteAction
import org.rekotlin.store

@Composable
fun HorizontalCadView(
    record: ExploreItem,
    maxWidth: Dp = 240.dp,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(maxWidth)
            .padding(horizontal = 4.dp)
    ) {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(record.BannerUrl ?: "https://picsum.photos/600")
                    .diskCacheKey(record.SeqNo.toString())
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,

                )
            Column(modifier = Modifier.height(120.dp)) {


                Text(
                    record.Title ?: "",
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    record.Subtitle ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }


}

@Preview
@Composable
fun Preview_HorizontalCadView() {
    HorizontalCadView(
        record = ExploreItem(
            Title = "Title",
            Subtitle = "Subtitle",
            BannerUrl = "https://picsum.photos/600"
        )
    )
}
