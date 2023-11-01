package repositories;

import models.BookModel;
import models.UserModel;
import models.UserRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class BookRepository {

    ElasticArray<BookModel> books = new ElasticArray<BookModel>();

    public void add(BookModel book) {
        if (Validate.validateName(book.getName())
                && Validate.validateName(book.getAuthor())
                && Validate.validateName(book.getBookText())
                && Validate.validateDate(book.getPublishingDate())) {
            books.add(book);
        }

    }

    public void remove(BookModel book) {
        books.remove(books.findIndexOf(x -> x.equals(book)));
    }

    public BookModel getBook(Predicate<BookModel> predicate){
        return books.find(predicate);
    }
    public List<BookModel> getBooks(Predicate<BookModel> predicate){
        return books.findAll(predicate);
    }

    public void editBook(BookModel book, UserModel user) {
        if (user.getUserRole() == UserRole.Admin) {

            BookModel targetBook = books.find(x -> x.equals(book.getId()));


            targetBook.setPublishingDate(targetBook.getPublishingDate().equals(book.getPublishingDate()) ? targetBook.getPublishingDate() : book.getPublishingDate());
            targetBook.setName(targetBook.getName().equals(book.getName()) ? targetBook.getName() : book.getName());
            targetBook.setAuthor(targetBook.getAuthor().equals(book.getAuthor()) ? targetBook.getAuthor() : book.getAuthor());
            targetBook.setBookText(targetBook.getBookText().equals(book.getBookText()) ? targetBook.getBookText() : book.getBookText());
        }
    }

    public void pickUpBook(BookModel book,UserModel user) {

        book.setPickUpDate(Date.from(Instant.now()), user);
    }

    public void dropBook(BookModel book) {
        book.setDropDate(Date.from(Instant.now()));
    }
}
