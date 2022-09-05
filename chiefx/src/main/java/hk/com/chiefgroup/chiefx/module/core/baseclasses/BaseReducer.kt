package hk.com.chiefgroup.chiefx.module.core.baseclasses

abstract class BaseReducer<
        ActionType,
        StateType,
        ViewType
> {
    open fun willUpdateView(action: ActionType, state: StateType?, view: ViewType): StateType? { return null }
    open fun onUpdate(action: ActionType, state: StateType?, payload: Any?): StateType? { return null }
    fun updateView(action: ActionType, state: StateType?, view: ViewType) {}
}