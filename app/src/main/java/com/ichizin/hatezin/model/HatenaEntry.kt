package com.ichizin.hatezin.model

import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 *
 * @author ichizin
 */
class HatenaEntry {

    var title: String? = null

    var link: String? = null

    var description: String? = null

    @SerializedName("content:encoded")
    var content_encoded: String? = null

    @SerializedName("dc:date")
    var date: Date? = null

    @SerializedName("dc:subject")
    var subject: String? = null

    @SerializedName("hatena:bookmarkcount")
    var bookmarkCount: Int = 0
}
