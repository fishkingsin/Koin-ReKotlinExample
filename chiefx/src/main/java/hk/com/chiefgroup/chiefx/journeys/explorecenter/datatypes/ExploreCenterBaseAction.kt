package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

//enum class ExploreCenterAction {
//    none,getExplores, state, error
//}


sealed class ExploreCenterBaseAction
interface ExploreCenterAction {
    data class None(val number: Int = 0) : ExploreCenterBaseAction()
    data class GetExplores(val number: Int = 0) : ExploreCenterBaseAction()
    data class NavigateToResults(val number: Int = 0) : ExploreCenterBaseAction()
    data class State(val state: hk.com.chiefgroup.chiefx.module.core.baseclasses.State) : ExploreCenterBaseAction()
    data class Error(val error: Error) : ExploreCenterBaseAction()
    data class SelectedCategory(val category: ExploreCategory? = null) : ExploreCenterBaseAction()
}