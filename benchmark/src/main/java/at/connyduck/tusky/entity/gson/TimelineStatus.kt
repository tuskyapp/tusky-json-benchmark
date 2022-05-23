/* Copyright 2017 Andrew Dawson
 *
 * This file is a part of Tusky.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * Tusky is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Tusky; if not,
 * see <http://www.gnu.org/licenses>. */

package at.connyduck.tusky.entity.gson

import at.connyduck.tusky.entity.moshi.Attachment
import at.connyduck.tusky.entity.moshi.Card
import at.connyduck.tusky.entity.moshi.Emoji
import at.connyduck.tusky.entity.moshi.HashTag
import at.connyduck.tusky.entity.moshi.Poll
import at.connyduck.tusky.entity.moshi.TimelineAccount
import at.connyduck.tusky.entity.moshi.TimelineStatus
import com.google.gson.annotations.SerializedName
import java.util.Date

data class TimelineStatus(
    val id: String,
    val url: String?, // not present if it's reblog
    val account: TimelineAccount,
    @SerializedName("in_reply_to_id") var inReplyToId: String?,
    @SerializedName("in_reply_to_account_id") val inReplyToAccountId: String?,
    val reblog: TimelineStatus?,
    val content: String,
    @SerializedName("created_at") val createdAt: Date,
    val emojis: List<Emoji>,
    @SerializedName("reblogs_count") val reblogsCount: Int,
    @SerializedName("favourites_count") val favouritesCount: Int,
    var reblogged: Boolean,
    var favourited: Boolean,
    var bookmarked: Boolean,
    var sensitive: Boolean,
    @SerializedName("spoiler_text") val spoilerText: String,
    val visibility: Visibility,
    @SerializedName("media_attachments") var attachments: ArrayList<Attachment>,
    val mentions: List<Mention>,
    val tags: List<HashTag>?,
    val application: Application?,
    val pinned: Boolean?,
    val muted: Boolean?,
    val poll: Poll?,
    val card: Card?
) {
     enum class Visibility(val num: Int) {
         UNKNOWN(0),
         @SerializedName("public")
         PUBLIC(1),
         @SerializedName("unlisted")
         UNLISTED(2),
         @SerializedName("private")
         PRIVATE(3),
         @SerializedName("direct")
         DIRECT(4);
     }

    data class Mention(
        val id: String,
        val url: String,
        @SerializedName("acct") val username: String,
        @SerializedName("username") val localUsername: String
    )

    data class Application(
        val name: String,
        val website: String?
    )
}
