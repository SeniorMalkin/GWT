package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.filter.filter;
import com.mySampleApplication.client.functional.functional;

import java.util.ArrayList;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

    private list mylist;
    private functional myfunctional;
    private filter myfilter;
    public void onModuleLoad() {
        HorizontalPanel panel = new HorizontalPanel();
        myfilter =  new filter();

        panel.add(myfilter);

        AsyncCallback<ArrayList<LibraryDB.Book>> asyncCallback = new AsyncCallback<ArrayList<LibraryDB.Book>>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println(caught.toString());
            }

            @Override
            public void onSuccess(ArrayList<LibraryDB.Book> result) {
                mylist = new list(result);
                myfunctional = new functional();
                myfilter.setDataProvider(mylist.getList());
                myfunctional.setDataProvider(mylist.getList());
                myfunctional.setSelectionModel(mylist.getModel());
                panel.add(mylist);
                panel.add(myfunctional);
                RootPanel.get().add(panel);
            }
        };
        MySampleApplicationService.App.getInstance().getLibrary("Hello, World!", asyncCallback);


        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //

    }

    public void sort(){

    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
