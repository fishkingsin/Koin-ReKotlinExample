package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.app.view.Main
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.android.ext.android.get
import org.koin.core.component.KoinScopeComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*

class ExploreCenterFragment : Fragment(), Routable, Subscriber<ExploreCenterState>, KoinScopeComponent {
    companion object {
        const val repositoryQualifier = "Repository"
        const val scopeId = "ExploreCenterActivity"
        const val storeQualifier = "Store"
        const val viewModelQualifier = "ViewModel"
    }
    override var scope: Scope = getKoin().createScope(
        scopeId, named("ExploreCenter")
    )

    private val store: Store<ExploreCenterState> = scope.get(
        qualifier = named(storeQualifier)
    )
    private lateinit var router: Subscriber<NavigationState>

    private var appStore: Store<AppState>? = get()

    // demo
    private val state: ObservableState<ExploreCenterState> = scope.get(
        qualifier = named(viewModelQualifier)
    ) { parametersOf(store ) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = router(rootRoutable = this)
        // subscribe it to navigationState changes
        store.subscribe(router, selector = {
            select { navigationState ?: NavigationState() }
        })

        store.dispatch(SetRouteAction(Route("Root")))

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    // in android compose scenario
                    ExploreCenter(state)
                }
            }
        }
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
            appStore?.dispatch(SetRouteAction(Route(RouteSegment("App", "Greeting"))))
        }
    }

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        return this
    }

    override fun newState(state: ExploreCenterState) {
        println("newState $state")
    }

}