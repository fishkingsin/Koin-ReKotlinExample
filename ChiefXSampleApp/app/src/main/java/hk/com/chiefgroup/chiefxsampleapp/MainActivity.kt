package hk.com.chiefgroup.chiefxsampleapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.app.route.AppRoutable
import hk.com.chiefgroup.chiefx.journeys.app.view.Main
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.NavigationState
import org.rekotlin.router.Route
import org.rekotlin.router.router


class MainActivity : AppCompatActivity(), Subscriber<AppState> {
    private val store : Store<AppState> = get()
    private val viewModel : ObservableState<AppState> by inject { parametersOf(store) }

    private val routable: AppRoutable by inject { parametersOf(store, this)  }

    override fun onDestroy() {
        super.onDestroy()
        store.unsubscribe(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        val router = router(routable) { handler.post(it) }
        store.subscribe(router) { select { navigationState ?: NavigationState(Route("App")) } }

        store.subscribe(this)
        print(viewModel.current)
        setContent {
            MaterialTheme {
                // in android compose scenario
                Main()
            }
        }

    }

    override fun newState(state: AppState) {
        println("MainActivity $state")
    }
}

