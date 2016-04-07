package com.ichizin.hatezin.presenter;

import com.ichizin.hatezin.model.HatenaEntry;
import com.ichizin.hatezin.model.HatenaFeed;
import com.ichizin.hatezin.repository.HatenaClient;
import com.ichizin.hatezin.util.HatenaCategory;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 *
 *
 * @author ichizin
 */
public class EntryDetailPresenter implements Presenter<EntryDetailPresenter.EntryDetailView> {

    private HatenaClient client;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private EntryDetailView entryDetailView;

    @Inject
    public EntryDetailPresenter(HatenaClient hatenaClient) {
        this.client = hatenaClient;
    }

    public void getData(final HatenaCategory hatenaCategory) {

        Observable<HatenaFeed> feed = null;
        switch (hatenaCategory) {
            case HOT_ENTRY:
                feed = client.getHotEntry();
                break;
            case SOCIAL:
                feed = client.getSocial();
                break;
            case ECONOMICS:
                feed = client.getEconomics();
                break;
            case LIFE:
                feed = client.getLife();
                break;
            case KNOWLEDGE:
                feed = client.getKnowledge();
                break;
            case IT:
                feed = client.getIt();
                break;
            case ENTERTAINMENT:
                feed = client.getEntertaiment();
                break;
            case GAME:
                feed = client.getGame();
                break;
            case FUN:
                feed = client.getFun();
                break;
            default:
                feed = client.getHotEntry();
        }

        feed.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HatenaFeed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HatenaFeed hatenaFeed) {
                        entryDetailView.renderData(hatenaFeed.items);
                    }
                });

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

    @Override
    public void attachView(EntryDetailView view) {
        this.entryDetailView = view;
    }

    public interface EntryDetailView {

        void renderData(List<HatenaEntry> datas);
    }
}
