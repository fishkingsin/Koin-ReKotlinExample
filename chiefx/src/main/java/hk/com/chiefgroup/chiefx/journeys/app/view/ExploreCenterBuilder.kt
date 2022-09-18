package hk.com.chiefgroup.chiefx.journeys.app.view

import androidx.compose.runtime.Composable
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.androidx.compose.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.rekotlin.Store

@Composable
fun ExploreCenterBuilder() {
    val scope: Scope = getKoin().createScope(
        ExploreCenterActivity.scopeId, named("ExploreCenter")
    )

    val store: Store<ExploreCenterState> = scope.get(
        qualifier = named(ExploreCenterActivity.storeQualifier)
    )
    val state: ObservableState<ExploreCenterState> = scope.get(
        qualifier = named(ExploreCenterActivity.viewModelQualifier)
    ) { parametersOf(store ) }
    ExploreCenter(state)
}