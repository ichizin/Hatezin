package com.ichizin.hatezin.util

import java.util.EnumSet

/**
 * HatenaCategory Enum class

 * @author ichizin
 */
enum class HatenaCategory internal constructor(val category: String) {

    HOT_ENTRY("hot_entry"),
    SOCIAL("social"),
    ECONOMICS("economics"),
    LIFE("life"),
    KNOWLEDGE("knowledge"),
    IT("it"),
    ENTERTAINMENT("entertainment"),
    GAME("game"),
    FUN("fun"),
    VIDEO("video"),
    OTHER("other");


    companion object {

        internal var categories = EnumSet.allOf(HatenaCategory::class.java)

        fun valueOfCategory(category: String): HatenaCategory {
            for (hatenaCategory in categories) {
                if (hatenaCategory.category == category) {
                    return hatenaCategory
                }
            }
            return OTHER
        }
    }

}
