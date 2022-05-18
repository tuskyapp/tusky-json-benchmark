package at.connyduck.tusky.entity.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class Poll(
    val id: String,
    @Json(name = "expires_at") val expiresAt: Date?,
    val expired: Boolean,
    val multiple: Boolean,
    @Json(name = "votes_count") val votesCount: Int,
    @Json(name = "voters_count") val votersCount: Int?, // nullable for compatibility with Pleroma
    val options: List<PollOption>,
    val voted: Boolean,
    @Json(name = "own_votes") val ownVotes: List<Int>?
)

@JsonClass(generateAdapter = true)
data class PollOption(
    val title: String,
    @Json(name = "votes_count") val votesCount: Int
)
