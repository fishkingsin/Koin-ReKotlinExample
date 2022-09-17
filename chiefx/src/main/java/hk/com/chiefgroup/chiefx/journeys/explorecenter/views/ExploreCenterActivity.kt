package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.os.Bundle
import android.util.Log
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
import org.koin.core.component.KoinScopeComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*


class ExploreCenterActivity: AppCompatActivity(), Subscriber<Boolean>, Routable,
    KoinScopeComponent {
    companion object {
        const val repositoryQualifier = "Repository"
        const val scopeId = "ExploreCenterActivity"
        const val storeQualifier = "Store"
        const val viewModelQualifier = "ViewModel"
    }
    override var scope: Scope = getKoin().createScope(
        scopeId, named("ExploreCenter"))

    private val store: Store<ExploreCenterState> = scope.get(
        qualifier = named(storeQualifier))
    private lateinit var router: Subscriber<NavigationState>

    // demo
    private val state: ObservableState<ExploreCenterState> = scope.get(
        qualifier = named(viewModelQualifier)) { parametersOf(store )}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = router(rootRoutable = this)
        // subscribe it to navigationState changes
        store.subscribe(router, selector = {
            select { navigationState ?: NavigationState() }
        })


        store.dispatch(SetRouteAction(Route("Root")))

        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter()
            }
        }



        store.subscribe(this) {
            select {
                this.isLoading ?: false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        store.unsubscribe(this)
        scope?.close()

    }

    override fun newState(state: Boolean) {
        Log.d("Main", "newState $state")

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