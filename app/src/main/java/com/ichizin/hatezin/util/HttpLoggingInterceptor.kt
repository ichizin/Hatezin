package com.ichizin.hatezin.util

import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.*
import okio.Buffer
import timber.log.Timber
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ichizin on 16/03/22.

 * @author ichizin
 */
@Singleton
class HttpLoggingInterceptor(@Inject val connectivityManager: ConnectivityManager,
                          val level: Level = Level.NONE) : Interceptor {

    private val MAX_STALE = 30 * 24 * 60 * 60 // 30 days
    private val MAX_AGE = 60 + 5    // 5min
    private val UTF8 = Charset.forName("UTF-8")

    enum class Level {
        /** No logs. */
        NONE,
        /**
         * Logs request and response lines.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1 (3-byte body)
         *
         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END GET
         *
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    override fun intercept(chain: Interceptor.Chain?): Response? {

        val request = chain?.request()?.newBuilder()

        if (isConnected()) {
            request?.addHeader("cache-control", "public, max-age=" + MAX_AGE)

            if(level == Level.NONE) {
                return chain?.proceed(request?.build())
            }

            val logBody = level == Level.BODY
            val logHeaders = logBody || level == Level.HEADERS

            val requestBody: RequestBody? = chain?.request()?.body()
            val hasRequestBody = requestBody != null

            val connection: Connection?  = chain?.connection()

            val protpcol: Protocol;
            if (connection != null) {
                protpcol = connection.protocol()
            } else {
                protpcol = Protocol.HTTP_1_1
            }

            var requestStartMessage =
                    "-->" + chain?.request()?.method() + " " + requestPath(chain?.request()?.url()!!) + getProtpcolName(protpcol)

            if(!logHeaders && hasRequestBody) {
                requestStartMessage += "(" + requestBody?.contentLength() + " -byte body"
            }

            Timber.d(requestStartMessage)

            if(logHeaders) {

                val logHeaders: Headers? = chain?.request()?.headers()
                for (i in 0..logHeaders!!.size()) {
                    Timber.d(logHeaders.name(i)+ ": " + logHeaders.value(i))
                }

                var endMessage: String = "--> END " + chain?.request()?.method()
                if (logBody && hasRequestBody) {

                    var buffer: Buffer = Buffer()
                    requestBody?.writeTo(buffer)

                    var charset: Charset = UTF8
                    var contentType = requestBody?.contentType()
                    if(contentType != null) {
                        charset = contentType.charset(charset)
                    }

                    if(requestBody?.contentLength() != 0L) {
                        Timber.d(buffer.clone().readString(charset))
                    }
                    endMessage += " (" + buffer.size() + "-byte body)"
                }
                Timber.d(endMessage)
            }

            val startNs = System.nanoTime()
            val response: Response? = chain?.proceed(request?.build())
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

            val responseBody: ResponseBody? = response?.body()
            Timber.d("<--" + getProtpcolName(response!!.protocol()) + ' ' + response?.code() + ' '
            + response?.message() + " (" + tookMs + "ms"
            + requestBody?.contentLength() + "-byte body")

            if(logHeaders) {
                var headers = response.headers()
                for(i in 0..headers.size()) {
                    Timber.d(headers.name(i)+ ": " + headers.value(i))
                }
                var endMessage = "<-- END HTTP"
                if(logBody) {
                    var source = responseBody?.source()
                    source?.request(Long.MAX_VALUE)
                    var buffer = source?.buffer()

                    var charset = UTF8
                    val contentType: MediaType? = responseBody?.contentType()
                    if(contentType != null) {
                        charset = contentType.charset(UTF8)
                    }

                    if(responseBody?.contentLength() != 0L) {
                        Timber.d(buffer?.clone()?.readString(charset))
                    }
                    endMessage += " (" + buffer?.size() + "-byte body)"
                }
                Timber.d(endMessage)
            }
            return response
        } else {
            request?.addHeader("cache-control", "public, only-if-cached, max-stale=" + MAX_STALE)
        }
        return chain?.proceed(request?.build())

        throw UnsupportedOperationException()
    }

    protected fun isConnected(): Boolean {

        val networkInfo: NetworkInfo = this.connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    companion object {

        @JvmStatic private fun requestPath(httpUrl: HttpUrl): String {
            val path = httpUrl.encodedPath()
            val query = httpUrl.encodedQuery()
            return if(query != null) path + '?' + query  else  path;
        }

        @JvmStatic private fun getProtpcolName(protocol: Protocol): String {
            return if(protocol == Protocol.HTTP_1_0) "HTTP/1.0" else "HTTP/1.1"
        }
    }
}
