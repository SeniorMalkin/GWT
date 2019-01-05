package com.mySampleApplication.client.functional;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mySampleApplication.client.LibraryDB;
import com.mySampleApplication.client.functionalResources;

import java.util.Set;

public class functional extends Composite {
    @UiTemplate("functional.ui.xml")
    interface functionalUiBinder extends UiBinder<Widget, functional> {
    }
    @UiField(provided = true)
    final functionalResources res;

    private static functionalUiBinder ourUiBinder = GWT.create(functionalUiBinder.class);
    private  ListDataProvider<LibraryDB.Book> dataProvider;
    private  SingleSelectionModel<LibraryDB.Book> selectionModel;

    public functional() {
        this.res = GWT.create(functionalResources.class);
        res.style().ensureInjected();
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void setDataProvider(ListDataProvider<LibraryDB.Book> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setSelectionModel(SingleSelectionModel<LibraryDB.Book> selectionModel) {
        this.selectionModel = selectionModel;
    }
    @UiField
    Button remove;
    @UiHandler("remove")
    void doClickRemove(ClickEvent event){
        LibraryDB.Book selected = selectionModel.getSelectedObject();
        if (selected != null) {
            dataProvider.getList().remove(selected);
            selectionModel.clear();

        }
    }
}