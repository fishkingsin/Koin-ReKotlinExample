package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation

@Composable
fun ExploreCenter(
    viewModel: ExploreCenterViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "explore center landing") {
        composable("explore center landing") {
            ExploreCenterLanding(
                viewModel,

                // You could pass the nav controller to further composables,
                // but I like keeping nav logic in a single spot by using the hoisting pattern
                // hoisting probably won't work as well in deep hierarchies,
                // in which case CompositionLocal might be more appropriate
                onConfirm = { navController.navigate("result") },
            )
        }
        composable("result") {
            ExploreCenterDetail(
                viewModel.records.firstOrNull()?.Records ?: emptyList()
            )
        }
    }
}
