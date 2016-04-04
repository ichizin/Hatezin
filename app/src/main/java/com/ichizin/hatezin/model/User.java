package com.ichizin.hatezin.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by ichizin on 16/03/30.
 *
 * @author ichizin
 */
public class User extends BaseObservable {

    @Bindable
    private String name;

    @Bindable
    private String lastName;

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
