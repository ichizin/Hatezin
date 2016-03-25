package com.ichizin.hatezin.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ichizin on 16/03/25.
 *
 * @author ichizin
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
