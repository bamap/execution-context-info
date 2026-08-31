package ir.bamap.blu.info.execution.service

import ir.bamap.blu.info.execution.model.ExecutionContextInfo

interface ExecutionContextInfoService {
    fun getInfo(): ExecutionContextInfo
}