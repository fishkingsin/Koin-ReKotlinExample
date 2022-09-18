package hk.com.chiefgroup.chiefx.journeys.app.di

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.reducer.exploreCenterReducer
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.rekotlin.store
import org.rekotlin.thunkMiddleware

val exploreCenterModule: Module = module {
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