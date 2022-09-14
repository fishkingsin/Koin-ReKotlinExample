package hk.com.chiefgroup.chiefx.journeys.explorecenter.actions

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse
import org.rekotlin.Action

data class ExploreCenterRequestExplores(val unit: Unit = Unit) : Action {

}

data class ExploreCenterRequestExploresSuccess(var response: ExploresReposonse) : Action {

}

data class ExploreCenterRequestExploresFailed(var error: Throwable) : Action {

}

data class ExploreCenterRequestExploresStarted(val unit: Unit = Unit) : Action {

}

data class ExploreCenterRequestExploresEnded(val unit: Unit = Unit) : Action {

}

data class ExploreCenterSelectedCategory(val category: ExploreCategory) : Action {

}

data class ExploreCenterSelectedItem(val item: ExploreItem) : Action {

}

data class ExploreCenterRequestExploresExitWithResult(val unit: Unit = Unit) : Action {}