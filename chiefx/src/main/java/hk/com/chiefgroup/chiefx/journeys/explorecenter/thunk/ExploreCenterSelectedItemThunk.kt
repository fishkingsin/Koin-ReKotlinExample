package hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterSelectedItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import org.rekotlin.DispatchFunction
import org.rekotlin.Thunk

public class ExploreCenterSelectedItemThunk(val state: ExploreItem, val dispatcher: Dispatcher):
    Thunk<ExploreCenterState> {
    override fun invoke(dispatch: DispatchFunction, getState: () -> ExploreCenterState?) {
        dispatch(ExploreCenterSelectedItem(state))
        dispatcher.push("Detail")
    }
}