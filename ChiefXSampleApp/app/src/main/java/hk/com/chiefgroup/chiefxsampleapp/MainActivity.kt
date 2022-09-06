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

}
class MainActivity : AppCompatActivity(), ExploreCenterView {
    override lateinit var exploreCenterStore: ExploreCenterStoreImplementation
    override var key: String = "MainActivity"

    private val model: ExploreCenterViewModel by viewModels {
        ExploreCenterViewModelFactory(exploreCenterStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val store = CXExploreCenterStoreImplementation()
        exploreCenterStore = store

        setContent {
            MaterialTheme {
                // in android compose scenario
                ExploreCenter(model)
            }
        }

        exploreCenterStore.registerRepository(ExploreCenterRepositoryImplementation())
        exploreCenterStore.registerReducer(ExploreCenterGetExploresReducer(exploreCenterStore))
        exploreCenterStore.registerSaga(ExploreCenterGetExploresSaga(exploreCenterStore))
        // in traditional activity scenario
        exploreCenterStore.register(this)
        exploreCenterStore.viewDidLoad(this)
    }

    override fun updateExplores(exploreState: ExploreCenterState) {
        Log.d("MainActivity", "updateExplores $exploreState")
    }

    override fun updateName(name: String) {
        Log.d("MainActivity", "updateName $name")
    }

    override fun hideLoadingIndicator(type: String) {
        Log.d("MainActivity", "hideLoadingIndicator")
    }

    override fun showLoadingIndicator(type: String) {
        Log.d("MainActivity", "showLoadingIndicator")
    }

    override fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?) {
        Log.d("MainActivity", "showErrorMessage $errorCode")
    }


}




