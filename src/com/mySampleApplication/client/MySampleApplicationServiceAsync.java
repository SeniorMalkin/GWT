package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface MySampleApplicationServiceAsync {
    void getLibrary(String msg, AsyncCallback<ArrayList<LibraryDB.Book>> async);
    void sortLibrary(String type, AsyncCallback<ArrayList<LibraryDB.Book>> async);
}
