package at.connyduck.tusky.entity.moshi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HashTag(val name: String, val url: String)
