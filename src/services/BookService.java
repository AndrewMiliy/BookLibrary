package services;

import models.BookModel;
import models.UserModel;
import models.UserRole;
import repositories.BookRepository;
import repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Обновить информацию о книге (только для админа)
    public void updateBook(BookModel bookToUpdate, UserModel admin) {
        if (admin.getUserRole() == UserRole.ADMIN) {
            bookRepository.editBook(bookToUpdate, admin);
        } else {
            System.out.println("Только администратор может обновлять информацию о книге.");
        }
    }

    // Добавить книгу (только для админа)
    public void addBook(BookModel book, UserModel admin) {
        if (admin.getUserRole() == UserRole.ADMIN) {
            bookRepository.add(book);
            bookRepository.saveBooks();
        } else {
            System.out.println("Только администратор может добавить новую книгу.");
        }
    }

    // Удалить книгу (только для админа)
    public void deleteBook(int id, UserModel admin) {
        if (admin.getUserRole() == UserRole.ADMIN) {
            BookModel bookToDelete = bookRepository.getBook(b -> b.getId() == id);
            bookRepository.remove(bookToDelete);
            bookRepository.saveBooks();
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
        if (!book.isBusy()) {
            bookRepository.pickUpBook(book, user);
        } else {
            System.out.println("Эта книга уже занята другим пользователем.");
        }
    }

    // Возврат книги
    public boolean returnBook(BookModel book) {
        if (book.isBusy()) {
            bookRepository.dropBook(book);
            return true;
        } else {
            System.out.println("Эта книга не была взята напрокат.");
            return false;
        }
    }

    public BookModel getBookById(int id) {
        return bookRepository.getBook(b -> b.getId() == id);
    }

    public List<BookModel> searchBook(String title) {
        return bookRepository.getBooks(b -> b.getName().contains(title));
    }

    public boolean issueBookToUser(int bookId, int userId) {
        BookModel book = bookRepository.getBook(b -> b.getId() == bookId);

        if (book != null) {
            UserModel user = UserService.getUser(u -> u.getId() == userId);
            if (user != null) {
                bookRepository.pickUpBook(book, user);
                user.addBookId(bookId);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int bookId) {
        BookModel book = bookRepository.getBook(b -> b.getId() == bookId);
        if (book != null) {
            bookRepository.dropBook(book);
            return true;
        }
        return false;
    }
}

