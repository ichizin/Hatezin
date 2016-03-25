package com.ichizin.hatezin.presenter

import com.ichizin.hatezin.model.HatenaEntry
import com.ichizin.hatezin.model.HatenaFeed
import com.ichizin.hatezin.repository.HatenaClient
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 *
 *
 * @author ichizin
 */
class MainActivityPresenter : Presenter<MainActivityPresenter.MainActivityView> {

    val hatenaClient: HatenaClient

    @Inject
    constructor(hatenaClient: HatenaClient) {
        this.hatenaClient = hatenaClient
    }

    private var mainActivityView: MainActivityView? = null

    fun initialize() {

        getData()
    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun destroy() {

    }

    fun getData() {

        this.hatenaClient.getHotEntry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<HatenaFeed> {
                    override fun onCompleted() {

//                        throw UnsupportedOperationException()
                    }

                    override fun onNext(t: HatenaFeed?) {


//                        throw UnsupportedOperationException()
                    }

                    override fun onError(e: Throwable?) {
                        Timber.e(e?.message)

                        throw UnsupportedOperationException()
                    }
                })

    }

    override fun attachView(view: MainActivityView?) {


    }

    interface MainActivityView {

        fun loadData(results: List<HatenaEntry>)
    }

}


