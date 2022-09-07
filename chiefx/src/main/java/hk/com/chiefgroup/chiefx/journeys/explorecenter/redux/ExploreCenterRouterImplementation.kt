package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class ExploreCenterRouterImplementation: ExploreCenterRouter<ExploreCenterBaseAction>() {
    val action: ExploreCenterBaseAction = ExploreCenterAction.NavigateToResults()
    private val _navigateToResults = Channel<Boolean>(Channel.BUFFERED)

    override fun route(action: ExploreCenterBaseAction): Unit = runBlocking {
        navigateTo(action = action)
    }

    private suspend fun navigateTo(action: ExploreCenterBaseAction) {
        if (action == this.action) {
            _navigateToResults.send(true)
        }
    }

    val navigateToResults: Flow<Boolean> = _navigateToResults.receiveAsFlow()
}