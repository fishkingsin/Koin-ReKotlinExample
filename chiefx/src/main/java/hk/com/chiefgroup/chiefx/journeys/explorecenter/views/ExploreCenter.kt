package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel.ExploreCenterStateObservableStateViewModel
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun ExploreCenter(
    viewModel: ObservableStateViewModel<ExploreCenterState>
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "explore center landing") {
        composable("explore center landing") {
            LaunchedEffect("key") { // probably is a better way to set the key than hardcoding key...
                // temp solution on routing
//                viewModel.navigateToExploreCenterDetails
//                    .onEach { navController.navigate("details") }
//                    .collect()

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if (viewModel.current.isLoading == true) {
                    CircularProgressIndicator()
                } else {

                    ExploreCenterCategoryVerticalListView(viewModel.current.categories ?: emptyList(), viewModel)

                }
            }
        }
        composable("details") {
            ExploreCenterCategoryItemVerticalListView(
                viewModel.current.selectedCategory ?: ExploreCategory()
            )
        }
    }
}
