package com.mySampleApplication.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface functionalResources extends ClientBundle {
    interface MyCSS extends CssResource{
        String head_text();
        String text();
        String div();
        String input();
        String button();
        String hpanel();
    }
    @Source("functional/functional.css")
    MyCSS style();
}
