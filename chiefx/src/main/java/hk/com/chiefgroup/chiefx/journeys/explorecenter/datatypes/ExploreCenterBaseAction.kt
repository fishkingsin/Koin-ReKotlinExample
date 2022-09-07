package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseAction

//enum class ExploreCenterAction {
//    none,getExplores, state, error
//}


sealed class ExploreCenterBaseAction: BaseAction() {
}
interface ExploreCenterAction {
    data class None(val number: Int = 0) : ExploreCenterBaseAction() {
        override var key: String = "None"
    }
    data class GetExplores(val number: Int = 0) : ExploreCenterBaseAction() {
        override var key: String = "GetExplores"
    }
    data class NavigateToCategoryDetails(val category: ExploreCategory? = null ) : ExploreCenterBaseAction()
    {
        override var key: String = "NavigateToResults"

        override fun hashCode(): Int {
            return key.hashCode()
        }
        override fun equals(other: Any?) = this === other || other is SelectedCategory
                && category == other.category
    }
    data class State(val state: hk.com.chiefgroup.chiefx.module.core.baseclasses.State) : ExploreCenterBaseAction()
    {
        override var key: String = "State"
    }
    data class Error(val error: Error) : ExploreCenterBaseAction() {
        override var key: String = "Error"
    }

    data class SelectedCategory(val category: ExploreCategory? = null) : ExploreCenterBaseAction()
    {
        override var key: String = "SelectedCategory"
        override fun hashCode(): Int {
            return key.hashCode()
        }
        override fun equals(other: Any?) = this === other || other is SelectedCategory
                && category == other.category
    }
}