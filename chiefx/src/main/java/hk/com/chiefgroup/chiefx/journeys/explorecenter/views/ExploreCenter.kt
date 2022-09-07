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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ExploreCenter(
    viewModel: ExploreCenterViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "explore center landing") {
        composable("explore center landing") {
            LaunchedEffect("key") { // probably is a better way to set the key than hardcoding key...
                viewModel.navigateToExploreCenterDetails
                    .onEach { navController.navigate("details") }
                    .collect()
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator()
                } else {

                    ExploreCenterCategoryVerticalListView(viewModel.categories, viewModel)

                }
            }
        }
        composable("details") {
            ExploreCenterCategoryItemVerticalListView(
                viewModel.selectedCategory
            )
        }
    }
}
