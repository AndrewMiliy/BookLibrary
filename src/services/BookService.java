package services;

import models.BookModel;
import models.UserModel;
import models.UserRole;
import repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    // Обновить информацию о книге (только для админа)
    public void updateBook(BookModel bookToUpdate, UserModel admin) {
        if(admin.getUserRole() == UserRole.Admin) {
            bookRepository.editBook(bookToUpdate, admin);
        } else {
            System.out.println("Only admin can update the book details.");
        }
    }

    // Узнать, сколько времени книга находится у пользователя
    public long getBookDurationWithUser(BookModel book) {
        if (book.getPickUpDate() != null && book.getDropDate() == null) {
            return new Date().getTime() - book.getPickUpDate().getTime();
        } else if (book.getPickUpDate() != null) {
            return book.getDropDate().getTime() - book.getPickUpDate().getTime();
        }
        return 0;
    }

    // Добавить книгу (только для админа)
    public void addBook(String name, String author, Date publishingDate, String bookText, UserModel admin) {
        if(admin.getUserRole() == UserRole.Admin) {
            BookModel book = new BookModel(name, author, publishingDate, bookText);
            bookRepository.add(book);
        } else {
            System.out.println("Only admin can add a new book.");
        }
    }

    // Удалить книгу (только для админа)
    public void deleteBook(BookModel bookToDelete, UserModel admin) {
        if(admin.getUserRole() == UserRole.Admin) {
            bookRepository.remove(bookToDelete);
        } else {
            System.out.println("Удалить книгу может только администратор.");
        }
    }

    // Показать информацию о книге
    public BookModel showBookInfo(Predicate<BookModel> predicate) {
        return bookRepository.getBook(predicate);
    }

    // Поиск книги
    public List<BookModel> searchBooks(Predicate<BookModel> predicate) {
        return bookRepository.getBooks(predicate);
    }

    // Выдача книги пользователю
    public void lendBookToUser(BookModel book, UserModel user) {
        if(!book.isBusy()) {
            bookRepository.pickUpBook(book, user);
        } else {
            System.out.println("Эта книга уже занята другим пользователем.");
        }
    }

    // Возврат книги
    public void returnBook(BookModel book) {
        if(book.isBusy()) {
            bookRepository.dropBook(book);
        } else {
            System.out.println("Эта книга не была взята напрокат.");
        }
    }
}
