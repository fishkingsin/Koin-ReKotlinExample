package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterGetExploresReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterLoadedReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterLoadingReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterSelectCategoryReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterGetExploresSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterSelectCategorySaga

open class ExploreCenterStoreImplementation :
    ExploreCenterStore<ExploreCenterRepositoryImplementation>() {
    // Default implementation
    init {
        _router = ExploreCenterRouterImplementation()
        registerRepository(ExploreCenterRepositoryImplementation())
        registerReducers(
            listOf(
                ExploreCenterGetExploresReducer(this),
                ExploreCenterLoadingReducer(this),
                ExploreCenterLoadedReducer(this),
                ExploreCenterSelectCategoryReducer(this, _router),
            )
        )
        registerSagas(
            listOf(
                ExploreCenterGetExploresSaga(this),
                ExploreCenterSelectCategorySaga(this)
            )
        )
    }
}