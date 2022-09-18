package hk.com.chiefgroup.chiefxsampleapp

import android.app.Application
import hk.com.chiefgroup.chiefx.journeys.app.di.appModule
import hk.com.chiefgroup.chiefx.journeys.app.di.exploreCenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidLogger()
            androidContext(this@MainApplication)
            // use the Android context given there
            // load properties from assets/koin.properties file
            androidFileProperties()


            // modules
            modules(exploreCenterModule, appModule)

        }
    }


}
