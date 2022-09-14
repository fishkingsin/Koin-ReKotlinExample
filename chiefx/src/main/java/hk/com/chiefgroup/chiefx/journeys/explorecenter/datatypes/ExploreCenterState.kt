package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseRoutableState
import org.rekotlin.router.NavigationState

data class ExploreCenterState(
    override val navigationState: NavigationState? = null,
    val exploresResponse: ExploresReposonse? = null,
    val isLoading: Boolean? = null,
    val error: Throwable? = null,
    val categories: ArrayList<ExploreCategory>? = null,
    val selectedCategory: ExploreCategory? = null,
    val selectedItem: ExploreItem? = null,
): BaseRoutableState(navigationState)
