package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateFactory
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.createActivityScope
import org.koin.core.scope.Scope
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*


class ExploreCenterActivity: AppCompatActivity(), Subscriber<ExploreCenterState>, Routable,
    AndroidScopeComponent {
    override var scope: Scope? = null

    private val store: Store<ExploreCenterState> by inject()
    private lateinit var router: Subscriber<NavigationState>


    private val state: ObservableState<ExploreCenterState> by viewModels {
        ObservableStateFactory(store)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createActivityScope()

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

    override fun onDestroy() {
        super.onDestroy()
        scope?.close()
    }

    override fun newState(state: ExploreCenterState) {
//        Log.d("Main", "newState $state")

    }

    override fun changeRouteSegment(
        from: RouteSegment,
        to: RouteSegment,
        animated: Boolean
    ): Routable {
        println("changeRouteSegment $from $to")
        return this
    }

    override fun popRouteSegment(routeSegment: RouteSegment, animated: Boolean) {
        println("popRouteSegment $routeSegment")
        if (routeSegment.toString() == "Root") {
            store.unsubscribe(this)
            finish()

        }
    }

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        return this
    }
}