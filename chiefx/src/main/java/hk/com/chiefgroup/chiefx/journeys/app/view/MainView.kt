package hk.com.chiefgroup.chiefx.journeys.app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.app.route.AppRoutable
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.getViewModel


@Composable
fun Main(
    state: ObservableState<AppState> = getViewModel(),
    route: AppRoutable = getKoin().get()
) {
    val navigationController = rememberNavController()
    LaunchedEffect(key1 = "onMainViewLaunched", block = {
        state.navigateTo.onEach {
            println("Main $it")
            if (it == listOf("App", "ExploreCenterView")) {
                navigationController.navigate(it.last())
            }
            if (it == listOf("App")) {
                navigationController.navigate(it.last())
            }
        }.collect()
    })
    NavHost(navigationController, startDestination = "App") {
        composable("App") {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Main Activity") }) },
                content = { padding ->


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        ExploreCenterActivityButton()
                        ExploreCenterComposeButton()
                        ExploreCenterFragmentButton()
                    }


                }

            )

        }
        composable(AppRoutable.exploreCenterViewId) {
            route.buildExploreCenter()
        }
    }
}
