package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseState

data class ExploreCenterState(
    var exploresReposonse: ExploresReposonse? = null,
    var isLoading: Boolean = false
): BaseState() {

    var selectedCategory: ExploreCategory? = null
}