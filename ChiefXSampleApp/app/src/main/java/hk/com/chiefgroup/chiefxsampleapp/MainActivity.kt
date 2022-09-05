package hk.com.chiefgroup.chiefxsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterGetExploresReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.ExploreCenterReducerImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterGetExploresSaga
import hk.com.chiefgroup.chiefx.journeys.explorecenter.saga.ExploreCenterSagaImplementation

class CXExploreCenterStoreImplementation: ExploreCenterStoreImplementation() {

}
class MainActivity : AppCompatActivity(), ExploreCenterView {
    override lateinit var exploreCenterStore: ExploreCenterStoreImplementation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val store = CXExploreCenterStoreImplementation()
        store.registerRepository(ExploreCenterRepositoryImplementation())
        store.registerReducer(ExploreCenterGetExploresReducer(store))
        store.registerSaga(ExploreCenterGetExploresSaga(store))
        store.register(this)
        store.viewDidLoad(this)
        store.dispatch(getExplores())
    }

    override fun updateExplores(exploreState: ExploreCenterState) {
        Log.d("MainActivity", "$exploreState")
    }

    override fun updateName(name: String) {
        Log.d("MainActivity", "$name")
    }

    override fun hideLoadingIndicator(type: String) {
        TODO("Not yet implemented")
    }

    override fun showLoadingIndicator(type: String) {
        TODO("Not yet implemented")
    }

    override fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?) {
        TODO("Not yet implemented")
    }

    override var key: String
        get() = "MainActivity"
        set(value) {}
}