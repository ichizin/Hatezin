package com.ichizin.hatezin.presenter

/**
 *
 *
 * @author ichizin
 */
interface Presenter<T> {

    fun resume()

    fun pause()

    fun destroy()

    fun attachView(view: T)
}
