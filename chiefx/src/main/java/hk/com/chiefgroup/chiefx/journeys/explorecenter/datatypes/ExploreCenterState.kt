package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import org.rekotlin.router.NavigationState

data class ExploreCenterState(
    val navigationState: NavigationState? = null,
    val exploresResponse: ExploresReposonse? = null,
    val isLoading: Boolean? = null,
    val error: Throwable? = null,
    val categories: ArrayList<ExploreCategory>? = null,
    val selectedCategory: ExploreCategory? = null,
    val count: Int = 0,
)
