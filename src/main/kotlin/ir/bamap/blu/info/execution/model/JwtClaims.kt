package ir.bamap.blu.info.execution.model

open class JwtClaims : HashMap<String, Any?>() {
    open val sub: String?
        get() = getAsStringOrNull("sub")

    open val iss: String?
        get() = getAsStringOrNull("iss")

    open val azp: String?
        get() = getAsStringOrNull("azp")

    open val scope: String?
        get() = getAsStringOrNull("scope")

    open val jti: String?
        get() = getAsStringOrNull("jti")

    open val exp: Long
        get() = getAsStringOrNull("exp")?.toLong() ?: 0

    open val iat: Long
        get() = getAsStringOrNull("iat")?.toLong() ?: 0

    open val username: String
        get() = getAsStringOrNull("preferred_username") ?: "anonymous"

    protected fun getAsStringOrNull(key: String?): String? {
        val value = get(key) ?: return null
        return value.toString()
    }
}