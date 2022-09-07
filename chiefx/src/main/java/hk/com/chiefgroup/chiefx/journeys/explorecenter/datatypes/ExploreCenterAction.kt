package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.State

//enum class ExploreCenterAction {
//    none,getExplores, state, error
//}


sealed class ExploreCenterAction

data class none(val number: Int = 0): ExploreCenterAction()
data class getExplores(val number: Int = 0): ExploreCenterAction()
data class navigateToResults(val number: Int = 0): ExploreCenterAction()
data class state(val state: State): ExploreCenterAction()
data class error(val error: Error): ExploreCenterAction()
data class selectedCategory(val category: ExploreCategory): ExploreCenterAction()