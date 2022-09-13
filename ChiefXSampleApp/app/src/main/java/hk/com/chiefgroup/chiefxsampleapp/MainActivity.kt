package hk.com.chiefgroup.chiefxsampleapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.GetExploresThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenter
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateFactory
import org.rekotlin.Store
import org.rekotlin.Subscriber
import org.rekotlin.router.*
import org.rekotlin.store
import org.rekotlin.thunkMiddleware


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // in android compose scenario
                Main()
            }
        }

    }
}


@Composable
fun Main() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ExploreCenterButton()
    }
}

@Composable
fun ExploreCenterButton() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, ExploreCenterActivity::class.java))
    }) {
        Text(text = "Explore Center")
    }
}


