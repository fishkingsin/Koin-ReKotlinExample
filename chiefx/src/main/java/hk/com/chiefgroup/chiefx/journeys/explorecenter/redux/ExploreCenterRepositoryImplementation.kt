package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

class ExploreCenterRepositoryImplementation: ExploreCenterRepository() {
    override suspend fun getExplores(): Result<Any> {
        return Result.success(true)
    }

}
