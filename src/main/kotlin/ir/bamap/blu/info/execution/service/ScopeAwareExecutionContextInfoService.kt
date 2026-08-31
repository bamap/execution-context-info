package ir.bamap.blu.info.execution.service

import ir.bamap.blu.info.execution.model.ExecutionContextInfo
import org.springframework.beans.factory.ObjectProvider
import org.springframework.web.context.request.RequestContextHolder

open class ScopeAwareExecutionContextInfoService(
    private val requestServiceProvider: ObjectProvider<ExecutionContextInfoService>,
    private val scheduleRequestService: ScheduleInfoService = ScheduleInfoService(),
) : ExecutionContextInfoService {

    protected open fun getRequestService(): ExecutionContextInfoService {
        if (isRequestScope())
            return requestServiceProvider.getObject()

        return scheduleRequestService
    }

    protected open fun isRequestScope(): Boolean {
        return RequestContextHolder.getRequestAttributes() != null
    }

    override fun getInfo(): ExecutionContextInfo {
        return getRequestService().getInfo()
    }
}