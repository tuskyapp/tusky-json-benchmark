package at.connyduck.tusky.entity

import com.google.gson.annotations.SerializedName

/**
 * Same as [Account], but only with the attributes required in timelines.
 * Prefer this class over [Account] because it uses way less memory & deserializes faster from json.
 */
data class TimelineAccount(
    val id: String,
    @SerializedName("username") val localUsername: String,
    @SerializedName("acct") val username: String,
    @SerializedName("display_name") val displayName: String?, // should never be null per Api definition, but some servers break the contract
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