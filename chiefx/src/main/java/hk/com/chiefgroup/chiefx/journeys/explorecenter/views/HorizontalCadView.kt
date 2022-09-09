package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalCadView(title: String, subtitle: String) {

    Card {
        Column(Modifier.padding(10.dp)) {
            Text(title ?: "", fontWeight = FontWeight.W700)
            Text(subtitle ?: "")
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview_HorizontalCadView() {
    HorizontalCadView(title = "title", subtitle = "subtitle")
}
