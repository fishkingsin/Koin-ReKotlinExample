package hk.com.chiefgroup.chiefx.module.core.baseclasses

public interface BaseView {
    fun hideLoadingIndicator(type: String)
    fun showLoadingIndicator(type: String)
    fun showErrorMessage(type: String, errorCode: String, warnings: List<String>?)
    var key: String
}

