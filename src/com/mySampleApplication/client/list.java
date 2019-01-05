package com.mySampleApplication.client;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.*;

import java.util.List;

public class list extends Composite {


    @UiField(provided = true)
    CellTable<LibraryDB.Book> table;

    @UiField(provided = true)
    SimplePager pager;

    @UiTemplate("list.ui.xml")
    interface listUiBinder extends UiBinder<Widget, list> {
    }

    private static listUiBinder ourUiBinder = GWT.create(listUiBinder.class);

    private ListDataProvider<LibraryDB.Book> dataProvider;
    private SingleSelectionModel<LibraryDB.Book> selectionModel;
    public list(List<LibraryDB.Book> arr) {

        ProvidesKey<LibraryDB.Book> keyProvider = new ProvidesKey<LibraryDB.Book>() {
            public Object getKey(LibraryDB.Book t) {
                return t == null? null : t.getId();
            }
        };
        selectionModel = new SingleSelectionModel<>(keyProvider);
        dataProvider = new ListDataProvider<>(keyProvider);
        table = new CellTable<>(5,keyProvider);
        table.setWidth("90%", true);

        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        //table.setSelectionModel(selectionModel, DefaultSelectionEventManager.createCheckboxManager());


        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(table);

        initTableColumns();


        List<LibraryDB.Book> list = dataProvider.getList();
        list.addAll(arr);
        dataProvider.addDataDisplay(table);

        //horPanel.add(table);
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    ListDataProvider<LibraryDB.Book> getList(){
     return dataProvider;
    }
    SingleSelectionModel<LibraryDB.Book> getModel(){
        return selectionModel;
    }
    private void initTableColumns(){
        Column<LibraryDB.Book, Boolean> checkColumn = new Column<LibraryDB.Book, Boolean>(
                new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(LibraryDB.Book object) {
                // Get the value from the selection model.
                return selectionModel.isSelected(object);
            }
        };
        table.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        table.setColumnWidth(checkColumn, 3, Style.Unit.PX);

        TextColumn<LibraryDB.Book> authorColumn = new TextColumn<LibraryDB.Book>() {
            @Override
            public String getValue(LibraryDB.Book object) {
                return object.getAuthor();
            }
        };
        table.addColumn(authorColumn,"Author");
        table.setColumnWidth(authorColumn,16, Style.Unit.PX);

        TextColumn<LibraryDB.Book> titleColumn = new TextColumn<LibraryDB.Book>() {
            @Override
            public String getValue(LibraryDB.Book object) {
                return object.getTitle();
            }
        };
        table.addColumn(titleColumn,"Title");
        table.setColumnWidth(titleColumn,14, Style.Unit.PX);

        TextColumn<LibraryDB.Book> pageColumn = new TextColumn<LibraryDB.Book>() {
            @Override
            public String getValue(LibraryDB.Book object) {
                return Integer.toString(object.getPages());
            }
        };
        table.addColumn(pageColumn,"Pages");
        table.setColumnWidth(pageColumn,5, Style.Unit.PX);

        TextColumn<LibraryDB.Book> yearColumn = new TextColumn<LibraryDB.Book>() {
            @Override
            public String getValue(LibraryDB.Book object) {
                return Integer.toString(object.getYear());
            }
        };
        table.addColumn(yearColumn,"Year");
        table.setColumnWidth(yearColumn,5, Style.Unit.PX);

        TextColumn<LibraryDB.Book> dateColumn = new TextColumn<LibraryDB.Book>() {
            @Override
            public String getValue(LibraryDB.Book object) {
                return object.getDate().toString();
            }
        };
        table.addColumn(dateColumn,"Date added");
        table.setColumnWidth(dateColumn,17, Style.Unit.PX);
    }

    @UiField
    HorizontalPanel horPanel;
}