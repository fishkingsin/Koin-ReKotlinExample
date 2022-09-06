package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.getExplores
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ExploreCenterLanding(
    viewModel: ExploreCenterViewModel,
    onConfirm: () -> Unit
) {
    val textFieldState = remember { mutableStateOf(TextFieldValue()) }

    // We only want the event stream to be attached once
    // even if there are multiple re-compositions
    LaunchedEffect("key") { // probably is a better way to set the key than hardcoding key...
        viewModel.navigateToExploreCenterDetails
            .onEach { onConfirm() }
            .collect()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "What do you call a mexican cheese?")
        Text("${viewModel.isLoading}")
        TextField(
            value = textFieldState.value,
            onValueChange = { textFieldState.value = it }
        )
        if (viewModel.isLoading == true) {
            CircularProgressIndicator()
        } else {
            Button(onClick = { viewModel.getExplores() }) {
                Text(text = "Confirm")
            }
        }
    }
}

class MockExploreCenterStoreImplementation: ExploreCenterStoreImplementation() {

}

@Preview
@Composable
fun TestExploreCenterLandingPreview() {
    MaterialTheme() {
        ExploreCenterLanding(ExploreCenterViewModel(MockExploreCenterStoreImplementation()), {})
    }
}