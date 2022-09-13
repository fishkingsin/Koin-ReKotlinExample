package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateFactory
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*
import org.rekotlin.store
import org.rekotlin.thunkMiddleware

class ExploreCenterActivity : AppCompatActivity(), Subscriber<ExploreCenterState>, Routable {

    private lateinit var store: Store<ExploreCenterState>
    private lateinit var router: Subscriber<NavigationState>

    private val state: ObservableState<ExploreCenterState> by viewModels {
        ObservableStateFactory(store)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store = store(
            reducer = ::exploreCenterReducer,
            state = ExploreCenterState(),
            middleware = arrayOf(thunkMiddleware())
        )

        router = router(rootRoutable = this)
        // subscribe it to navigationState changes
        store.subscribe(router, selector = {
            select { navigationState ?: NavigationState() }
        })
        store.subscribe(this)

        store.dispatch(SetRouteAction(Route("Root")))

        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter(state)
            }
        }

        val repository = ExploreCenterRepositoryImplementation()
        store.dispatch(GetExploresThunk(repository))


    }
    override fun newState(state: ExploreCenterState) {
//        Log.d("Main", "newState $state")

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