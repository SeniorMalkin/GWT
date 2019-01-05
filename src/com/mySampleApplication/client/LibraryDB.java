package com.mySampleApplication.client;

import com.google.gwt.user.client.Random;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LibraryDB {

    private static class authorComparator implements Comparator<Book>{
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    }
    private static class titleComparator implements Comparator<Book>{
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    }
    private static class dateComparator implements Comparator<Book>{
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

    public static class Book implements Comparable<Book>, Serializable {
        public Book() {
            id++;
            this.author = null;
            this.title = null;
            this.pages = 0;
            this.year = 0;
            this.date = new Date();
        }
        int id = 0;
        String author;
        String title;
        int pages;
        int year;
        Date date;

        public Book(String author, String title, int pages, int year, Date date , int id){
            this.id = id;
            this.author = author;
            this.title = title;
            this.pages = pages;
            this.year = year;
            this.date = date;
        }

        @Override
        public int compareTo(Book o) {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Book){
                return this.id == ((Book)obj).id;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int res = 17;
            res = 37* res + id;
            res = 37* res + author.hashCode();
            res = 37* res + title.hashCode();
            res = 37* res + pages;
            res = 37* res + year;
            res = 37* res + date.hashCode();
            return res;
        }


        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Date getDate() {
            return date;
        }

        public int getId() {
            return id;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    private static LibraryDB single;
    public static LibraryDB get(){
        if(single == null){
            single = new LibraryDB();
        }
        return single;
    }

    private List<Book>  dataProvider = new ArrayList<>();
    private List<Book>  respProvider = new ArrayList<>();

    private LibraryDB(){
        generateBooks(35);
    }
    public void addBook(Book book){
        dataProvider.remove(book);
        dataProvider.add(book);
    }
    public  void removeBook(Book book){
        //List<Book> list = dataProvider.getList();
        dataProvider.remove(book);
    }

    public List<Book> getDataProvider() {
        return respProvider;
    }

    private void generateBooks(int count){
        //List<Book> list = dataProvider.getList();
        for(int i = 0; i < count; i++){
            dataProvider.add(generateBook(i));
        }
    }
/*
    public void refreshDisplays(){
        dataProvider.refresh();
    }
*/
    private Book generateBook(int key){
        String str = "MNBVCXZLKJHGFDSAPOIUYTREWQ";
        int year = 1000 + (17*key)%1018;
        Book b =new Book(str.substring(key%25),"Title", 111,year, new Date(),key);
        return b;
    }

    public void createResponse(){
        respProvider.addAll(dataProvider);
    }

    private Comparator<Book> comparator;

    public void sortByAuthor(){
        //List<Book> list = respProvider.getList();
        comparator = new authorComparator();
        respProvider.sort(comparator);
    }
    public void  sortByTitle(){
        //List<Book> list = respProvider.getList();
        comparator = new titleComparator();
        respProvider.sort(comparator);
    }
    public void sortByPages(){
        //List<Book> list = respProvider.getList();
        comparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return 0;
            }
        };
        respProvider.sort(comparator.thenComparingInt(Book::getPages));
    }
    public  void sortByYear(){
        //List<Book> list = respProvider.getList();
        comparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return 0;
            }
        };
        respProvider.sort(comparator.thenComparingInt(Book::getYear));
    }
    public void sortByDate(){
       // List<Book> list = respProvider.getList();
        comparator = new dateComparator();
        respProvider.sort(comparator);
    }
    public void queryBooksByAuthor(String author){
        //List<Book> list = respProvider.getList();
        for(Book book : respProvider){
            if(!book.getAuthor().equals(author)){
                respProvider.remove(book);
            }
        }
    }

}
