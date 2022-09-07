package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class ExploreCenterRouterImplementation: ExploreCenterRouter<ExploreCenterAction>() {
    val action: ExploreCenterAction = navigateToResults()
    private val _navigateToResults = Channel<Boolean>(Channel.BUFFERED)

    override fun route(action: ExploreCenterAction): Unit = runBlocking {
        navigateTo(action = action)
    }

    private suspend fun navigateTo(action: ExploreCenterAction) {
        if (action == this.action) {
            _navigateToResults.send(true)
        }
    }

    val navigateToResults: Flow<Boolean> = _navigateToResults.receiveAsFlow()
}