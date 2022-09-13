package hk.com.chiefgroup.chiefxsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity


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
    Scaffold(
        topBar = { TopAppBar(title = {Text("Main Activity")})  },
        content = { padding ->
            val navigationController = rememberNavController()
            NavHost(navigationController, startDestination = "main") {
                composable("main") {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        ExploreCenterButton()
                    }
                }
            }
        }
    )
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
