package hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import org.rekotlin.DispatchFunction
import org.rekotlin.Thunk
import org.rekotlin.router.Route
import org.rekotlin.router.RouteSegment
import org.rekotlin.router.SetRouteAction

public class ExploreCenterSelectedCategoryThunk(
    val state: ExploreCategory,
    private val dispatcher: Dispatcher): Thunk<ExploreCenterState> {
    override fun invoke(dispatch: DispatchFunction, getState: () -> ExploreCenterState?) {
        dispatch(ExploreCenterSelectedCategory(category = state))
        dispatcher.push("Category")
    }
}
