package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.dismissThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach




@Composable
fun ExploreCenter(
    state: ObservableState<ExploreCenterState>
) {
//    val navController = rememberNavController()
    when {
        state.current.navigationState?.route?.toString() == "Root" -> {

//    NavHost(navController, startDestination = "Root") {
//        composable("Root") {
//            LaunchedEffect("key") { // probably is a better way to set the key than hardcoding key...
            // temp solution on routing
//                state.navigateTo
//                    .onEach { segments ->
//                        Log.d("ExploreCenter", "navigate $segments")
//
//                            navController.navigate(segments.joinToString("/")) {
//                                if (navController.backQueue.size < segments.size) {
//                                    popUpTo(segments.last())
//                                }
//                            }
//
//                    }
//                    .collect()
//            }
            Root(state)
//        }
        }
        state.current.navigationState?.route.toString() == "Root/Category" -> {
//        composable("Root/Category") {
            ExploreCenterCategoryItemVerticalListView(
                state.current.selectedCategory ?: ExploreCategory(),
                state
            )
//        }
        }
//    }
    }
}

@Composable
fun Root(state: ObservableState<ExploreCenterState>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Explore Center")},
                navigationIcon = {Text(text = "Close", Modifier.clickable { state.dispatch(dismissThunk()) })}
            )},
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if (state.current.isLoading == true) {
                    CircularProgressIndicator()
                } else {

                    ExploreCenterCategoryVerticalListView(
                        state.current.categories ?: emptyList(),
                        state
                    )

                }
            }
        }
    )
}