package hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.*
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepository
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepositoryImplementation
import hk.com.chiefgroup.chiefx.journeys.explorecenter.views.ExploreCenterActivity
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.getKoin
import org.rekotlin.DispatchFunction
import org.rekotlin.Thunk


public class GetExploresThunk(
    private val repository: ExploreCenterRepository =
        getKoin()
            .getScope(ExploreCenterActivity.scopeId)
            .get<ExploreCenterRepositoryImplementation>(qualifier = named(ExploreCenterActivity.repositoryQualifier))
) : Thunk<ExploreCenterState> {
    override fun invoke(dispatch: DispatchFunction, getState: () -> ExploreCenterState?) {
        dispatch(ExploreCenterRequestExploresStarted())
        runBlocking {
            val result = repository.getExplores()
            when {
                result.isSuccess -> {
                    result.getOrNull()?.let { response ->
                        dispatch(ExploreCenterRequestExploresSuccess(response))
                    }
                }
                result.isFailure -> {
                    result.exceptionOrNull()?.let { exception ->
                        dispatch(ExploreCenterRequestExploresFailed(exception))
                    }
                }
            }
            dispatch(ExploreCenterRequestExploresEnded())
        }
    }
}

class DismissThunk : Thunk<ExploreCenterState> {
    override fun invoke(dispatch: DispatchFunction, getState: () -> ExploreCenterState?) {
        dispatch(ExploreCenterRequestExploresExitWithResult())
    }
}