package com.ichizin.hatezin.presenter;

/**
 *
 *
 * @author ichizin
 */
public interface Presenter<T> {

    void resume();

    void pause();

    void destroy();

    void attachView(T view);

}
