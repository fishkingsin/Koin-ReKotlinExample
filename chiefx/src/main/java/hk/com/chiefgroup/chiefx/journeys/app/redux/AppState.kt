package hk.com.chiefgroup.chiefx.journeys.app.redux

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseRoutableState
import org.rekotlin.router.NavigationState

data class AppState(
    override val navigationState: NavigationState? = null
): BaseRoutableState(navigationState)