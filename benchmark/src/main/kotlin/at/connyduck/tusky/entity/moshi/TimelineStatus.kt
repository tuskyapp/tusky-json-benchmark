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

package at.connyduck.tusky.entity.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class TimelineStatus(
    val id: String,
    val url: String?, // not present if it's reblog
    val account: TimelineAccount,
    @Json(name = "in_reply_to_id") var inReplyToId: String?,
    @Json(name = "in_reply_to_account_id") val inReplyToAccountId: String?,
    val reblog: TimelineStatus?,
    val content: String,
    @Json(name = "created_at") val createdAt: Date,
    val emojis: List<Emoji>,
    @Json(name = "reblogs_count") val reblogsCount: Int,
    @Json(name = "favourites_count") val favouritesCount: Int,
    var reblogged: Boolean,
    var favourited: Boolean,
    var bookmarked: Boolean,
    var sensitive: Boolean,
    @Json(name = "spoiler_text") val spoilerText: String,
    val visibility: Visibility,
    @Json(name = "media_attachments") var attachments: List<Attachment>,
    val mentions: List<Mention>,
    val tags: List<HashTag>?,
    val application: Application?,
    val pinned: Boolean?,
    val muted: Boolean?,
    val poll: Poll?,
    val card: Card?
) {
    @JsonClass(generateAdapter = false)
     enum class Visibility(val num: Int) {
        @Json(name = "unknown") UNKNOWN(0),
        @Json(name = "public") PUBLIC(1),
        @Json(name = "unlisted") UNLISTED(2),
        @Json(name = "private") PRIVATE(3),
        @Json(name = "direct") DIRECT(4);
     }

    @JsonClass(generateAdapter = true)
    data class Mention(
        val id: String,
        val url: String,
        @Json(name = "acct") val username: String,
        @Json(name = "username") val localUsername: String
    )

    @JsonClass(generateAdapter = true)
    data class Application(
        val name: String,
        val website: String?
    )
}
