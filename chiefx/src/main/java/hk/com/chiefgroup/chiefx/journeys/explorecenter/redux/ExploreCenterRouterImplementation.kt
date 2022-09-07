package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class ExploreCenterRouterImplementation: ExploreCenterRouter<ExploreCenterBaseAction>() {

    private val _navigateToCategoryDetails = Channel<Boolean>(Channel.BUFFERED)
    private val _dismiss = Channel<Boolean>(Channel.BUFFERED)

    override val navigateToCategoryDetails: Flow<Boolean> = _navigateToCategoryDetails.receiveAsFlow()
    override val dismiss: Flow<Boolean> = _dismiss.receiveAsFlow()


    override fun route(action: ExploreCenterBaseAction): Unit = runBlocking {
        navigateTo(action = action)
    }

    private suspend fun navigateTo(action: ExploreCenterBaseAction) {
        when (action) {
            is ExploreCenterAction.NavigateToCategoryDetails -> {
                _navigateToCategoryDetails.send(true)
            }
            is ExploreCenterAction.SelectedCategory -> {

            }
            else -> {
            }
        }
    }


}