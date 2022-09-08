package hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk

import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterRequestExploresEnded
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterRequestExploresFailed
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterRequestExploresStarted
import hk.com.chiefgroup.chiefx.journeys.explorecenter.actions.ExploreCenterRequestExploresSuccess
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.redux.ExploreCenterRepository
import kotlinx.coroutines.runBlocking
import org.rekotlin.DispatchFunction
import org.rekotlin.Thunk


public class GetExploresThunk(private val repository: ExploreCenterRepository): Thunk<ExploreCenterState> {
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