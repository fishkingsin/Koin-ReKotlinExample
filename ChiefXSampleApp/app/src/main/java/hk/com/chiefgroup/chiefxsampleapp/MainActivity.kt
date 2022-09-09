package hk.com.chiefgroup.chiefxsampleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel.ExploreCenterStateObservableStateViewModel
import hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel.ExploreCenterViewModelFactory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*
import org.rekotlin.store
import org.rekotlin.thunkMiddleware


class MainActivity : AppCompatActivity(), Subscriber<ExploreCenterState>, Routable {

    private lateinit var store: Store<ExploreCenterState>
    private val model: ExploreCenterStateObservableStateViewModel by viewModels {
        ExploreCenterViewModelFactory(store)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = store(
            reducer = ::ExploreCenterReducer,
            state = ExploreCenterState(),
            middleware = arrayOf(thunkMiddleware())
        )
// crash here
        /*val router = router(rootRoutable = this, Handler(Looper.getMainLooper())::post)
        // subscribe it to navigationState changes
        store.subscribe(router, selector = {
            select { navigationState ?: NavigationState() }
        })
*/
        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter(model)
            }
        }

        store.subscribe(this)

//        store.dispatch(SetRouteAction(Route("home", "user")))
    }
    override fun newState(state: ExploreCenterState) {
        Log.d("Main", "newState $state")

    }

    override fun changeRouteSegment(
        from: RouteSegment,
        to: RouteSegment,
        animated: Boolean
    ): Routable {
        TODO("Not yet implemented")
    }

    override fun popRouteSegment(routeSegment: RouteSegment, animated: Boolean) {
        TODO("Not yet implemented")
    }

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        TODO("Not yet implemented")
    }
}




