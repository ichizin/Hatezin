package com.ichizin.hatezin.util;

import android.content.Context;

import com.ichizin.hatezin.R;

import java.util.EnumSet;

import javax.inject.Inject;

/**
 * Created by ichizin on 16/03/30.
 *
 * @author ichizin
 */
public enum  HatenaCategory {

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

    private String category;

    HatenaCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    static EnumSet<HatenaCategory> categories = EnumSet.allOf(HatenaCategory.class);

    public static HatenaCategory valueOfId(String category) {

        for (HatenaCategory entity : categories) {
            if(entity.category.equals(category)) {
                return entity;
            }
        }
        return OTHER;
    }

    public String getName(Context context) {

        HatenaCategory category = HatenaCategory.valueOfId(this.category);

        switch (category) {
            case HOT_ENTRY:
                return context.getResources().getString(R.string.category_hot);
            case SOCIAL:
                return context.getResources().getString(R.string.category_social);
            case ECONOMICS:
                return context.getResources().getString(R.string.category_economics);
            case LIFE:
                return context.getResources().getString(R.string.category_life);
            case KNOWLEDGE:
                return context.getResources().getString(R.string.category_knowledge);
            case IT:
                return context.getResources().getString(R.string.category_it);
            case ENTERTAINMENT:
                return context.getResources().getString(R.string.category_entertainment);
            case GAME:
                return context.getResources().getString(R.string.category_game);
            case FUN:
                return context.getResources().getString(R.string.category_fun);
            default:
                return "";
        }
    }

}
