package models;

import repositories.IdCounter;

import java.util.Date;

public class BookModel {
    private int id;
    private String name;
    private String author;
    private Date publishingDate;
    private Date pickUpDate;
    private Date dropDate;
    private String bookText;
    private UserModel user;

    public BookModel(String name, String author, Date publishingDate, String bookText) {
        this.id = IdCounter.Instance.getNextId();
        setName(name);
        setAuthor(author);
        setPublishingDate(publishingDate);
        setBookText(bookText);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate, UserModel user) {
        this.user = user;
        this.pickUpDate = pickUpDate;
    }

    public Date getDropDate() {
        return dropDate;
    }

    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

 public boolean isBusy () {
        return getUser() != null;
 }

}
