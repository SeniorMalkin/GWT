package com.mySampleApplication.client.filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.mySampleApplication.client.LibraryDB;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.client.filterResources;
import com.mySampleApplication.client.functional.functional;
import com.mySampleApplication.client.list;

import java.util.ArrayList;
import java.util.List;

public class filter extends Composite {
    @UiTemplate("filter.ui.xml")
    interface filterUiBinder extends UiBinder<Widget, filter> {
    }
    @UiField(provided = true)
    final filterResources res;

    private static filterUiBinder ourUiBinder = GWT.create(filterUiBinder.class);
    private ListDataProvider<LibraryDB.Book> dataProvider;

    public filter() {
        this.res = GWT.create(filterResources.class);
        res.style().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    public void setDataProvider(ListDataProvider<LibraryDB.Book> list){
        dataProvider = list;
    }

    @UiField
    CheckBox author;
    @UiField
    CheckBox title;
    @UiField
    CheckBox year;
    @UiField
    CheckBox date;
    @UiField
    Button buttonSort;

    @UiHandler("buttonSort")
    void doClickSort(ClickEvent event){
        AsyncCallback<ArrayList<LibraryDB.Book>> asyncCallback = new AsyncCallback<ArrayList<LibraryDB.Book>>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println(caught.toString());
            }

            @Override
            public void onSuccess(ArrayList<LibraryDB.Book> result) {
                dataProvider.setList(result);
            }
        };
        String str = "author";
        if(title.getValue()){
            str ="title";
        }
        if(year.getValue()){
            str = "year";
        }
        if(date.getValue()){
            str = "date";
        }
        MySampleApplicationService.App.getInstance().sortLibrary(str, asyncCallback);
    }
    @UiHandler("author")
    void doClickAuthor(ClickEvent event){
        title.setValue(false);
        year.setValue(false);
        date.setValue(false);
    }
    @UiHandler("title")
    void doClickTitle(ClickEvent event){
        author.setValue(false);
        year.setValue(false);
        date.setValue(false);
    }
    @UiHandler("year")
    void doClickYear(ClickEvent event){
        title.setValue(false);
        author.setValue(false);
        date.setValue(false);
    }
    @UiHandler("date")
    void doClickDate(ClickEvent event){
        title.setValue(false);
        year.setValue(false);
        author.setValue(false);
    }
}