package ir.bamap.blu.info.execution.service

import ir.bamap.blu.info.execution.model.JwtClaims
import ir.bamap.blu.info.execution.model.RequestInfo
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tools.jackson.core.JacksonException
import tools.jackson.databind.ObjectMapper
import java.util.*

open class RequestInfoService(
    protected open val request: HttpServletRequest
) : ExecutionContextInfoService {

    companion object {
        @JvmStatic
        protected val objectMapper = ObjectMapper()

        @JvmStatic
        protected val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    protected val defaultRequestId = UUID.randomUUID().toString()

    open fun getId(): String {
        return request.getHeader("X-Request-ID")?.takeIf { it.isNotEmpty() } ?: return defaultRequestId
    }

    open fun getUri(): String {
        return request.requestURI
    }

    open fun getMethod(): String {
        return request.method
    }

    open fun getAuthorizationTokenOrNull(): String? {
        return getJwtTokenOrNull()
    }

    open fun getCurrentUsername(): String {
        return getJwtClaimsOrNull()?.username ?: "anonymous"
    }

    override fun getInfo(): RequestInfo {
        return RequestInfo(getId(), getUri(), getMethod(), getCurrentUsername())
    }

    protected fun getJwtClaimsOrNull(): JwtClaims? {
        val jwtToken = getJwtTokenOrNull() ?: return null

        val sections: Array<String> = jwtToken.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (sections.size < 2) return null

        val secondSection = Base64.getDecoder().decode(sections[1])
        try {
            return objectMapper.readValue<JwtClaims>(String(secondSection), JwtClaims::class.java)
        } catch (e: JacksonException) {
            logger.error("cannot parse token: $jwtToken", e)
            return null
        }
    }

    protected fun getJwtTokenOrNull(): String? {
        val token: String = request.getHeader("Authorization") ?: return null

        return "Bearer " + token.replace("Bearer ", "")
    }
}