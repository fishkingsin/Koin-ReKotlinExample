package hk.com.chiefgroup.chiefx.module.core.baseclasses

abstract class BaseSaga<ActionType, StateType, ViewType> {
    open suspend fun onDispatch(action: ActionType, state: StateType?, view: List<ViewType>?) {}
}
