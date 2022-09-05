package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class ExploreCenterRouterImplementation: ExploreCenterRouter() {
    private val _navigateToResults = Channel<Boolean>(Channel.BUFFERED)
    override val navigateToResults: Flow<Boolean> = _navigateToResults.receiveAsFlow()
}