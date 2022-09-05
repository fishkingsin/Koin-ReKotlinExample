package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseState

data class ExploreCenterState(
    var name: String = "",
    var isLoading: Boolean = false,
): BaseState() {

}