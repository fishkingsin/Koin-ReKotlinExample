package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation

@Composable
fun ExploreCenterDetail(
    viewModel: ExploreCenterViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        viewModel.records.firstOrNull()?.Records?.firstOrNull()?.let {
            Text(text = it.Name ?: "")
        }
    }
}

@Preview
@Composable
fun TestExploreCenterDetailPreview() {
    MaterialTheme() {
        ExploreCenterDetail(ExploreCenterViewModel(MockExploreCenterStoreImplementation()))
    }
}