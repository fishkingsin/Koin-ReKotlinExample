package hk.com.chiefgroup.chiefxsampleapp

import android.app.Application
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import org.rekotlin.store
import org.rekotlin.thunkMiddleware

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
            modules(exploreCenterModule)

        }
    }
    private val exploreCenterModule: Module = module {
        scope(named("ExploreCenter")) {
            scoped(qualifier = named(ExploreCenterActivity.storeQualifier)) {
                store(
                    reducer = ::exploreCenterReducer,
                    state = ExploreCenterState(),
                    middleware = arrayOf(thunkMiddleware())
                )
            }
            scoped(qualifier = named(ExploreCenterActivity.viewModelQualifier)) { parameters ->
                ObservableState<ExploreCenterState>(parameters.get())
            }

            scoped(qualifier = named(ExploreCenterActivity.repositoryQualifier)) {
                ExploreCenterRepositoryImplementation()
            }
        }

    }
}
