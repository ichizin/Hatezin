package com.ichizin.hatezin.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *
 * @author ichizin
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
