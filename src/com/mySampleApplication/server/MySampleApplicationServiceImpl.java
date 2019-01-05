package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.LibraryDB;
import com.mySampleApplication.client.MySampleApplicationService;

import java.util.ArrayList;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
    private LibraryDB dataBase;
    // Implementation of sample interface method
    public ArrayList<LibraryDB.Book> getLibrary(String msg) {
        dataBase = LibraryDB.get();
        dataBase.createResponse();
        return new ArrayList<>(dataBase.getDataProvider());
    }

    @Override
    public ArrayList<LibraryDB.Book> sortLibrary(String type) {
        if("author".equals(type)){
            dataBase.sortByAuthor();
        }
        if("title".equals(type)){
            dataBase.sortByTitle();
        }
        if("year".equals(type)){
            dataBase.sortByYear();
        }
        if("date".equals(type)){
            dataBase.sortByDate();
        }
        return new ArrayList<>(dataBase.getDataProvider());
    }
}