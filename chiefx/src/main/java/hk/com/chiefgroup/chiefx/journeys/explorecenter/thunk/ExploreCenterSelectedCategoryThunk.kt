package hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import org.rekotlin.DispatchFunction
import org.rekotlin.Thunk
import org.rekotlin.router.Route
import org.rekotlin.router.RouteSegment
import org.rekotlin.router.SetRouteAction

public class ExploreCenterSelectedCategoryThunk(val state: ExploreCategory, private val route: Route): Thunk<ExploreCenterState> {
    override fun invoke(dispatch: DispatchFunction, getState: () -> ExploreCenterState?) {
        dispatch(ExploreCenterSelectedCategory(category = state))
        var segment = route.segments.map { it.id }.toMutableList()
        segment.add("Category")

        dispatch(SetRouteAction(Route(segment.map { RouteSegment(it) })))
    }
}
