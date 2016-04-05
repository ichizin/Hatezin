package com.ichizin.hatezin.presenter;

import com.ichizin.hatezin.model.HatenaEntry;
import com.ichizin.hatezin.model.HatenaFeed;
import com.ichizin.hatezin.repository.HatenaClient;
import com.ichizin.hatezin.util.HatenaCategory;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func9;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 *
 * @author ichizin
 */
public class HotEntryPresenter implements Presenter<HotEntryPresenter.HotEntryView>{

    private HotEntryView hotEntryView;
    private HatenaClient client;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Inject
    public HotEntryPresenter(HatenaClient client) {
        this.client = client;
    }


    public void initialize() {
        this.getData();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        subscriptions.unsubscribe();
    }

    @Override
    public void destroy() {

    }

    @SuppressWarnings("unchecked")
    public void getData() {

        Subscription subscription = Observable.zip(
                client.getHotEntry(),
                client.getSocial(),
                client.getEconomics(),
                client.getLife(),
                client.getKnowledge(),
                client.getIt(),
                client.getEntertaiment(),
                client.getGame(),
                client.getFun(),
                new Func9<HatenaFeed, HatenaFeed, HatenaFeed, HatenaFeed, HatenaFeed, HatenaFeed,
                        HatenaFeed, HatenaFeed, HatenaFeed, List<HatenaEntry>>() {
                    @Override
                    public List<HatenaEntry> call(HatenaFeed hotEntry, HatenaFeed social, HatenaFeed economics,
                                                  HatenaFeed life, HatenaFeed knowledge, HatenaFeed it,
                                                  HatenaFeed entertaiment, HatenaFeed game, HatenaFeed fun) {

                        List<HatenaEntry> results = new LinkedList<HatenaEntry>();
                        // HotEntry
                        results.add(getTitleEntry(HatenaCategory.HOT_ENTRY));
                        results.addAll(hotEntry.getTake(5));

                        // Social
                        results.add(getTitleEntry(HatenaCategory.SOCIAL));
                        results.addAll(social.getTake(5));

                        // Economics
                        results.add(getTitleEntry(HatenaCategory.ECONOMICS));
                        results.addAll(economics.getTake(5));

                        // Life
                        results.add(getTitleEntry(HatenaCategory.LIFE));
                        results.addAll(life.getTake(5));

                        // knowledge
                        results.add(getTitleEntry(HatenaCategory.KNOWLEDGE));
                        results.addAll(knowledge.getTake(5));

                        // IT
                        results.add(getTitleEntry(HatenaCategory.IT));
                        results.addAll(it.getTake(5));

                        // Entertaiment
                        results.add(getTitleEntry(HatenaCategory.ENTERTAINMENT));
                        results.addAll(entertaiment.getTake(5));

                        // Game
                        results.add(getTitleEntry(HatenaCategory.GAME));
                        results.addAll(game.getTake(5));

                        // Fun
                        results.add(getTitleEntry(HatenaCategory.FUN));
                        results.addAll(fun.getTake(5));

                        return results;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HatenaEntry>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<HatenaEntry> hatenaEntries) {

                        for (HatenaEntry entry : hatenaEntries) {
                            if (entry.isTitle()) {
                                Timber.i("*************************");
                                Timber.i("*****" + entry.getHatenaCategory().getCategory() + "**********");

                            } else {
                                Timber.i(entry.getTitle());
                            }
                        }
                        hotEntryView.renderData(hatenaEntries);
                    }
                });
        subscriptions.add(subscription);

    }

    @Override
    public void attachView(HotEntryView view) {
        this.hotEntryView = view;
    }

    public interface HotEntryView {

        void renderData(List<HatenaEntry> feeds);
    }

    private HatenaEntry getTitleEntry(HatenaCategory category) {

        HatenaEntry entry = new HatenaEntry();
        entry.setIsTitle(true);
        entry.setHatenaCategory(category);
        return entry;
    }

}
