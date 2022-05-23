package at.connyduck.tusky.entity.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimelineAccount(
    val id: String,
    @Json(name = "username") val localUsername: String,
    @Json(name = "acct") val username: String,
    @Json(name = "display_name") val displayName: String?, // should never be null per Api definition, but some servers break the contract
    val url: String,
    val avatar: String,
    val bot: Boolean = false,
    val emojis: List<Emoji>? = emptyList(), // nullable for backward compatibility
) {

    val name: String
        get() = if (displayName.isNullOrEmpty()) {
            localUsername
        } else displayName
}