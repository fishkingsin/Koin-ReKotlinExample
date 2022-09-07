package hk.com.chiefgroup.chiefxsampleapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterGetExploresReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterLoadedReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterLoadingReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterSelectCategoryReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRouterImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterStoreImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterGetExploresSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterSelectCategorySaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterViewModel
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterViewModelFactory

class CXExploreCenterStoreImplementation : ExploreCenterStoreImplementation() {
    // customization
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

//
class MainActivity : AppCompatActivity() {
    private lateinit var exploreCenterStore: ExploreCenterStoreImplementation

    private val model: ExploreCenterViewModel by viewModels {
        ExploreCenterViewModelFactory(exploreCenterStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exploreCenterStore = CXExploreCenterStoreImplementation()
        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter(model)
            }
        }
        exploreCenterStore.dispatch(ExploreCenterAction.GetExplores(0))
    }
}




