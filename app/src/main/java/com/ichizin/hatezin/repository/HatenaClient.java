package com.ichizin.hatezin.repository;

import com.ichizin.hatezin.model.HatenaFeed;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 *
 *
 * @author ichizin
 */
public class HatenaClient {

    private static final String BASE_URL = "http://b.hatena.ne.jp";
    HatebuService hatebuService;

    @Inject
    HatenaClient(OkHttpClient okHttpClient) {

        Retrofit hatebuRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        hatebuService = hatebuRetrofit.create(HatebuService.class);
    }

    public Observable<HatenaFeed> getHotEntry() {
        return this.hatebuService.getHotEntry();
    }

    public Observable<HatenaFeed> getSocial() {
        return this.hatebuService.getSocial();
    }

    public Observable<HatenaFeed> getEconomics() {
        return this.hatebuService.getEconomy();
    }

    public Observable<HatenaFeed> getLife() {
        return this.hatebuService.getLife();
    }

    public Observable<HatenaFeed> getKnowledge() {
        return this.hatebuService.getKnowledge();
    }

    public Observable<HatenaFeed> getIt() {
        return this.hatebuService.getIt();
    }

    public Observable<HatenaFeed> getEntertaiment() {
        return this.hatebuService.getEntertainment();
    }

    public Observable<HatenaFeed> getGame() {
        return this.hatebuService.getGame();
    }

    public Observable<HatenaFeed> getFun() {
        return this.hatebuService.getFun();
    }

    public Observable<HatenaFeed>getVideo() {
        return this.hatebuService.getVideo();
    }


    interface HatebuService {

        @GET("/hotentry.rss")
        Observable<HatenaFeed> getHotEntry();

        @GET("/hotentry/social.rss")
        Observable<HatenaFeed> getSocial();

        @GET("/hotentry/economics.rss")
        Observable<HatenaFeed> getEconomy();

        @GET("/hotentry/life.rss")
        Observable<HatenaFeed> getLife();

        @GET("/hotentry/knowledge.rss")
        Observable<HatenaFeed> getKnowledge();

        @GET("/hotentry/it.rss")
        Observable<HatenaFeed> getIt();

        @GET("/hotentry/entertainment.rss")
        Observable<HatenaFeed> getEntertainment();

        @GET("/hotentry/game.rss")
        Observable<HatenaFeed> getGame();

        @GET("/hotentry/fun.rss")
        Observable<HatenaFeed> getFun();

        @GET("/video.rss")
        Observable<HatenaFeed> getVideo();

    }

}
