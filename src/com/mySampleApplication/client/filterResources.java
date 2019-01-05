package com.mySampleApplication.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface filterResources extends ClientBundle {
    public interface MyCss extends CssResource{
        String text();
        String head_text();
        String div();
        String button();
    }
    @Source("filter/filter.css")
    MyCss style();
}
