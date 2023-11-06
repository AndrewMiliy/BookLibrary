package models;

import repositories.IdCounter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String author;
    private LocalDate publishingDate;
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private String bookText;
    private UserModel user;

    public BookModel(String name, String author, LocalDate publishingDate, String bookText) {
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

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate, UserModel user) {
        this.user = user;
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropDate() {
        return dropDate;
    }

    public void setDropDate(LocalDate dropDate) {
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

    @Override
 public String toString()
 {
    return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", author='" + author + '\'' +
            ", publishingDate=" + publishingDate +
            ", pickUpDate=" + pickUpDate +
            ", dropDate=" + dropDate +
            ", bookText='" + bookText + '\'' +
            ", user=" + user +
            '}';
 }

}
