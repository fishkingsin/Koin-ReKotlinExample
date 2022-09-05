package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

open class ExploreCenterStoreImplementation: ExploreCenterStore<ExploreCenterRepositoryImplementation>() {
    override val router: ExploreCenterRouterImplementation? = ExploreCenterRouterImplementation()
}