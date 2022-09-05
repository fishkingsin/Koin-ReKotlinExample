package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class ExploreCenterRouterImplementation: ExploreCenterRouter<ExploreCenterAction>() {
    private val _navigateToResults = Channel<Boolean>(Channel.BUFFERED)
    override fun navigateToResults(action: ExploreCenterAction): Unit = runBlocking {
        _navigateToResults.send(true)
    }

    override val navigateToResults: Flow<Boolean> = _navigateToResults.receiveAsFlow()
}