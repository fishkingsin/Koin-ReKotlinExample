package hk.com.chiefgroup.chiefx.journeys.app.di

import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import hk.com.chiefgroup.chiefx.journeys.app.redux.AppState
import hk.com.chiefgroup.chiefx.journeys.app.redux.appReducer
import hk.com.chiefgroup.chiefx.journeys.app.route.AppRoutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import org.koin.core.module.Module
import org.koin.dsl.module
import org.rekotlin.store
import org.rekotlin.thunkMiddleware

val appModule: Module = module {
    single {
        store<AppState>(
            reducer = ::appReducer,
            state = AppState(),
            middleware = arrayOf(thunkMiddleware())
        )
    }
    single { parameters ->
        ObservableState<AppState>(parameters.get())
    }
    single { parameters ->
        AppRoutable(parameters.get(), MainScope(), Dispatchers.IO, parameters.get())

    }
}