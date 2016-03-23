package com.ichizin.hatezin.repository

import javax.inject.Inject

import okhttp3.OkHttpClient
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * HatenaClient

 * @author ichizin
 */
class HatenaClient {

    val HOT_ENTRY_URL = "http://b.hatena.ne.jp/hotentry.rss"
    val SOCIAL_URL = "http://b.hatena.ne.jp/hotentry/social.rss"
    val ECONOMICS_URL = "http://b.hatena.ne.jp/hotentry/economics.rss"
    val LIFE_URL = "http://b.hatena.ne.jp/hotentry/life.rss"
    val KNOWLEDGE_URL = "http://b.hatena.ne.jp/hotentry/knowledge.rss"
    val IT_URL = "http://b.hatena.ne.jp/hotentry/it.rss"
    val ENTER_TAINMENT_URL = "http://b.hatena.ne.jp/hotentry/entertainment.rss"
    val GAME_URL = "http://b.hatena.ne.jp/hotentry/game.rss"
    val FUN_URL = "http://b.hatena.ne.jp/hotentry/fun.rss"
    val VIDEO_URL = "http://b.hatena.ne.jp/video.rss"

    val BASE_URL = "http://b.hatena.ne.jp/"

    @Inject
    constructor(okHttpClient: OkHttpClient) {

//        var retrofit: Retrofit = Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .build()
    }

    interface HatebuServide {


    }
}

