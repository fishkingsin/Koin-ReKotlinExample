package hk.com.chiefgroup.chiefxsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class CXExploreCenterStoreImplementation: ExploreCenterStoreImplementation() {

}
class MainActivity : AppCompatActivity(), ExploreCenterView {
    override lateinit var exploreCenterStore: ExploreCenterStoreImplementation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val store = CXExploreCenterStoreImplementation()

        setContent {
            MaterialTheme {
                // in android compose scenario
                MyApp(store)
            }
        }

        store.registerRepository(ExploreCenterRepositoryImplementation())
        store.registerReducer(ExploreCenterGetExploresReducer(store))
        store.registerSaga(ExploreCenterGetExploresSaga(store))
        // in traditional activity scenario
        store.register(this)
        store.viewDidLoad(this)
    }

    override fun updateExplores(exploreState: ExploreCenterState) {
        Log.d("MainActivity", "$exploreState")
    }

    override fun updateName(name: String) {
        Log.d("MainActivity", "$name")
    }

    override fun hideLoadingIndicator(type: String) {
        TODO("Not yet implemented")
    }

    override fun showLoadingIndicator(type: String) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?) {
        TODO("Not yet implemented")
    }

    override var key: String = "MainActivity"
}


@Composable
fun MyApp(
    store: ExploreCenterStoreImplementation
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "question") {
        composable("question") {
            QuestionDestination(
                store = store,
                // You could pass the nav controller to further composables,
                // but I like keeping nav logic in a single spot by using the hoisting pattern
                // hoisting probably won't work as well in deep hierarchies,
                // in which case CompositionLocal might be more appropriate
                onConfirm = { navController.navigate("result") },
            )
        }
        composable("result") {
            ResultDestination(
                store
            )
        }
    }
}


@Composable
fun QuestionDestination(
    store: ExploreCenterStoreImplementation,
    onConfirm: () -> Unit
) {
    val textFieldState = remember { mutableStateOf(TextFieldValue()) }

    // We only want the event stream to be attached once
    // even if there are multiple re-compositions
    LaunchedEffect("key") { // probably is a better way to set the key than hardcoding key...
        store.router?.navigateToResults
            ?.onEach { onConfirm() }
            ?.collect()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "What do you call a mexican cheese?")
        Text("${store.state.collectAsState().value?.isLoading}")
        TextField(
            value = textFieldState.value,
            onValueChange = { textFieldState.value = it }
        )
        if (store.state.collectAsState().value?.isLoading == true) {
            CircularProgressIndicator()
        } else {
            Button(onClick = { store.dispatch(getExplores()) }) {
                Text(text = "Confirm")
            }
        }
    }
}


@Composable
fun ResultDestination(
    store: ExploreCenterStoreImplementation
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        store.state.collectAsState().value?.name?.let { Text(text = it) }
    }
}