package at.connyduck.tusky.entity.gson

import at.connyduck.tusky.entity.moshi.PollOption
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Poll(
    val id: String,
    @SerializedName("expires_at") val expiresAt: Date?,
    val expired: Boolean,
    val multiple: Boolean,
    @SerializedName("votes_count") val votesCount: Int,
    @SerializedName("voters_count") val votersCount: Int?, // nullable for compatibility with Pleroma
    val options: List<PollOption>,
    val voted: Boolean,
    @SerializedName("own_votes") val ownVotes: List<Int>?
)

data class PollOption(
    val title: String,
    @SerializedName("votes_count") val votesCount: Int
)
