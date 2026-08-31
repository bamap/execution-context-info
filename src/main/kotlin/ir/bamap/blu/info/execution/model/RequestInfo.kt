package ir.bamap.blu.info.execution.model

open class RequestInfo(
    override val id: String = "",
    open val uri: String = "",
    open val method: String = "",
    open val currentUsername: String = "",
): ExecutionContextInfo() {
}