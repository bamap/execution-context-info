package ir.bamap.blu.info.execution.service

import ir.bamap.blu.info.execution.model.ExecutionContextInfo

class ScheduleInfoService: ExecutionContextInfoService {
    override fun getInfo(): ExecutionContextInfo {
        return ExecutionContextInfo(Thread.currentThread().threadId())
    }
}