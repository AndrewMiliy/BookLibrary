package repositories;

import models.BookModel;
import models.UserModel;
import models.UserRole;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class BookRepository {

    ElasticArray<BookModel> books = new ElasticArray<BookModel>();

    private final String filename = "BOOKDB.txt";

    public BookRepository() {
        this.books = new ElasticArray<>(); // Создание нового списка книг
    }

    public void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            books =  (ElasticArray<BookModel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public boolean add(BookModel book) {
        if (Validate.validateName(book.getName())
                && Validate.validateName(book.getAuthor())
                && Validate.validateName(book.getBookText())) {
            books.add(book);
            return true;
        }
        return false;
    }

    public void remove(BookModel book) {

        books.remove(books.findIndexOf(x -> x.equals(book)));
    }

    public BookModel findBook(Predicate<BookModel> predicate){
        return books.find(predicate);
    }
    public List<BookModel> findBooks(Predicate<BookModel> predicate){
        return books.findAll(predicate);
    }

    public int getBooksCount() {
        return books.size();
    }

    public boolean editBook(BookModel book, UserModel user) {
        if (user.getUserRole() == UserRole.ADMIN) {
            if (!Validate.validateName(book.getName()) || !Validate.validateName(book.getAuthor()) || !Validate.validateName(book.getBookText())) {
                return false;
            }
                BookModel targetBook = books.find(x -> x.equals(book.getId()));


                targetBook.setPublishingDate(targetBook.getPublishingDate().equals(book.getPublishingDate()) ? targetBook.getPublishingDate() : book.getPublishingDate());
                targetBook.setName(targetBook.getName().equals(book.getName()) ? targetBook.getName() : book.getName());
                targetBook.setAuthor(targetBook.getAuthor().equals(book.getAuthor()) ? targetBook.getAuthor() : book.getAuthor());
                targetBook.setBookText(targetBook.getBookText().equals(book.getBookText()) ? targetBook.getBookText() : book.getBookText());

                return true;
            }

        return false;
    }

    public void pickUpBook(BookModel book,UserModel user) {

        book.setPickUpDate(LocalDate.now(), user);
    }
    

    public void dropBook(BookModel book) {
        book.setDropDate(LocalDate.now());
    }

    public ElasticArray<BookModel> getAllBooks() {
        return books;
    }
}
