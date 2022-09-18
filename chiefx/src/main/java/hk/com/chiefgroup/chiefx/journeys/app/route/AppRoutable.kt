package hk.com.chiefgroup.chiefx.journeys.app.route

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.app.view.ExploreCenterBuilder
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterFragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.koin.java.KoinJavaComponent.getKoin
import org.rekotlin.Store
import org.rekotlin.router.Routable
import org.rekotlin.router.RouteSegment
import kotlin.collections.set

private typealias Pop = () -> Unit

class AppRoutable(
    private val store: Store<AppState>,
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
//    private val root: ViewGroup
) : Routable {
    companion object {
        const val exploreCenterViewId = "ExploreCenterView"
        const val exploreCenterActivityId = "ExploreCenterActivity"
        const val exploreCenterFragmentId = "ExploreCenterFragment"
    }
//    private val homeScreen by lazy { HomeScreen(root) }
//    private val historyScreen by lazy { HistoryScreen(root) }
    private val popStack = mutableMapOf<String, Pop>()

    override fun pushRouteSegment(routeSegment: RouteSegment, animated: Boolean): Routable {
        println("pushRouteSegment $routeSegment")
        println("routeSegment.id ${routeSegment.id}")
        val pop = when (routeSegment.id) {
            "App" -> showHome()
            exploreCenterActivityId -> startExploreCenterActivity()
            exploreCenterViewId -> startExploreCenterView()
            exploreCenterFragmentId -> startExploreCenterFragment()
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
            println("ExploreCenterActivity popped")
            getKoin().getScope(ExploreCenterActivity.scopeId).close()
        }
    }

    private fun startExploreCenterView(): Pop {
        return {
            println("startExploreCenterView popped")
            getKoin().getScope(ExploreCenterActivity.scopeId).close()
        }
    }

    private fun startExploreCenterFragment(): Pop {
        (context as? AppCompatActivity)
        ?.supportFragmentManager
        ?.beginTransaction()
            ?.add(android.R.id.content, ExploreCenterFragment())
            ?.commit()
        return {
            println("ExploreCenter Fragment popped")
            getKoin().getScope(ExploreCenterActivity.scopeId).close()
        }
    }

    private fun showHome(): Pop {
        return {}
    }
    @Composable
    fun buildExploreCenter() {
        ExploreCenterBuilder()
    }
}