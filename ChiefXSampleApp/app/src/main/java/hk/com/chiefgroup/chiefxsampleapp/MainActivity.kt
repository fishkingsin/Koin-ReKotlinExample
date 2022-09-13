package hk.com.chiefgroup.chiefxsampleapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*
import org.rekotlin.store
import org.rekotlin.thunkMiddleware


class MainActivity : AppCompatActivity(), Subscriber<ExploreCenterState>, Routable {

    private lateinit var store: Store<ExploreCenterState>
    private lateinit var router: Subscriber<NavigationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = store(
            reducer = ::exploreCenterReducer,
            state = ExploreCenterState(),
            middleware = arrayOf(thunkMiddleware())
        )
// crash here
        router = router(rootRoutable = this, Handler(Looper.getMainLooper())::post)
        // subscribe it to navigationState changes
        store.subscribe(router, selector = {
            select { navigationState ?: NavigationState() }
        })
        store.subscribe(this)

        store.dispatch(SetRouteAction(Route("root")))

        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter(ObservableState(store))
            }
        }
        val repository = ExploreCenterRepositoryImplementation()
        store.dispatch(GetExploresThunk(repository))


    }
    override fun newState(state: ExploreCenterState) {
        Log.d("Main", "newState $state")

    }

    override fun changeRouteSegment(
        from: RouteSegment,
        to: RouteSegment,
        animated: Boolean
    ): Routable {
        return this
    }

    override fun popRouteSegment(routeSegment: RouteSegment, animated: Boolean) {
        println(routeSegment)
    }

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        return this
    }
}




