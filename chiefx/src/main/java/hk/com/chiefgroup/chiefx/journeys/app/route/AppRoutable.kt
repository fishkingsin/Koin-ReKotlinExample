package hk.com.chiefgroup.chiefx.journeys.app.route

import android.content.Context
import android.content.Intent
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.rekotlin.Store
import org.rekotlin.router.*
import kotlin.collections.mutableMapOf
import kotlin.collections.set

private typealias Pop = () -> Unit

class AppRoutable(
    private val store: Store<AppState>,
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
//    private val root: ViewGroup
) : Routable {
//    private val homeScreen by lazy { HomeScreen(root) }
//    private val historyScreen by lazy { HistoryScreen(root) }
    private val popStack = mutableMapOf<String, Pop>()

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        println("pushRouteSegment $routeSegment")
        println("routeSegment.id ${routeSegment.id}")
        val pop = when (routeSegment.id) {
            "App" -> showHome()
            "ExploreCenterActivity" -> startExploreCenterActivity()
            else -> null
        }
        pop?.let { popStack[routeSegment.id] = it }
        return this
    }

    override fun popRouteSegment(routeSegment: RouteSegment, animated: Boolean) {
        println("popRouteSegment $routeSegment")
        popStack[routeSegment.id]?.let {
            it()
            popStack.remove(routeSegment.id)
        }
    }

    override fun changeRouteSegment(
        from: RouteSegment,
        to: RouteSegment,
        animated: Boolean
    ): Routable {
        popRouteSegment(from, animated)
        pushRouteSegment(to, animated)
        return this
    }

    private fun startExploreCenterActivity(): Pop {
        val intent = Intent(context, ExploreCenterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context.startActivity(intent)
        return {

        }
//        root.attach(historyScreen.view)
//
//        val presenter = subscriber<List<User>> {
//            historyScreen.updateHistory(it)
//        }
//
//        store.subscribe(presenter) { select { history } }
//
//        return {
//            root.detach(historyScreen.view)
//            store.unsubscribe(presenter)
//        }
    }

    private fun showHome(): Pop {
        return {}
//        root.attach(homeScreen.view)
//
//        val presenter = HomeScreenPresenter(homeScreen)
//        // in a real application these behaviors would not be in the router
//        // they would be in a separate object that encapsulates user input business logic.
//        // But alas, this is a sample app, introducing more indirection makes it less readable.
//        homeScreen.random { store.dispatch(FetchRandomUser(scope, dispatcher)) }
//        homeScreen.goToHistory { store.dispatch(SetRouteAction(Route("history"))) }
//
//        store.subscribe(presenter)
//
//        return {
//            root.detach(homeScreen.view)
//            store.unsubscribe(presenter)
//        }
    }
}