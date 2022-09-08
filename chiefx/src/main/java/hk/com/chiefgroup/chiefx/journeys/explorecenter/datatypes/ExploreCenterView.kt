package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import hk.com.chiefgroup.chiefx.module.core.baseclasses.BaseView
import org.rekotlin.Store

interface ExploreCenterView: BaseView {
    var store: Store<ExploreCenterState>
    fun updateExploresCategories(categories: ArrayList<ExploreCategory>)
    fun updateExplores(exploreItem: ArrayList<ExploreItem>)
    fun updateSelectedCategory(category: ExploreCategory)
}