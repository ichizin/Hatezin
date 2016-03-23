package com.ichizin.hatezin.util

import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ichizin on 16/03/23.
 * @author ichizin
 *
 */
@Singleton
class RequestInterceptor(@Inject val connectivityManager: ConnectivityManager): Interceptor  {

    private val MAX_STALE = 30 * 24 * 60 * 60 // 30 days
    private val MAX_AGE = 60 + 5    // 5min

    override fun intercept(chain: Interceptor.Chain?): Response? {

        val request = chain?.request()?.newBuilder()

        if (isConnected()) {
            request?.addHeader("cache-control", "public, max-age=" + MAX_AGE)
        } else {
            request?.addHeader("cache-control", "public, only-if-cached, max-stale=" + MAX_STALE)
        }

            throw UnsupportedOperationException()
    }

    protected fun isConnected(): Boolean {

        val networkInfo: NetworkInfo = this.connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

}
