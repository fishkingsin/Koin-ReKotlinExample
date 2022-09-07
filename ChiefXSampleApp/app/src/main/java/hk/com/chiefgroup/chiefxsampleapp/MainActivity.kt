package hk.com.chiefgroup.chiefxsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterViewModel
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class CXExploreCenterStoreImplementation: ExploreCenterStoreImplementation() {
    init {
        registerRepository(ExploreCenterRepositoryImplementation())
        registerReducers(listOf(
            ExploreCenterGetExploresReducer(this),
            ExploreCenterLoadingReducer(this),
            ExploreCenterLoadedReducer(this)
        ))
        registerSaga(ExploreCenterGetExploresSaga(this))
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
    }
}




