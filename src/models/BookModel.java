package models;

import java.util.Date;

public class BookModel {
    private int ID;

    private String Name;
    private String Author;
    private Date PublishingDate;
    private Date PickupDate;
    private Date DropDate;
    private String BookText;
    private String User;

    public BookModel(int ID, String name, String author, Date publishingDate, String bookText) {
        this.setID(ID);
        setName(name);
        setAuthor(author);
        setPublishingDate(publishingDate);
        setBookText(bookText);
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getPublishingDate() {
        return PublishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        PublishingDate = publishingDate;
    }

    public Date getPickupDate() {
        return PickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        PickupDate = pickupDate;
    }

    public Date getDropDate() {
        return DropDate;
    }

    public void setDropDate(Date dropDate) {
        DropDate = dropDate;
    }

    public String getBookText() {
        return BookText;
    }

    public void setBookText(String bookText) {
        BookText = bookText;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

 public boolean isBusy () {
        return getUser() == null ? false:true;
 }

}
