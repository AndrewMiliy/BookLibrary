package test;

import models.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.BookRepository;

import java.time.LocalDate;


public class TestBookRepository {

    BookModel book;

    BookRepository books;


    String startName = "Heart of the Dragon";
    String startAuthor = "Kirill Klevanskii";
    LocalDate startPublishingDate = LocalDate.of(2019,03,22);
    String startBookText = "This story begins from a ...";

    @BeforeEach
    void setUp() {
        books = new BookRepository();
        book = new BookModel(startName, startAuthor, startPublishingDate, startBookText);
    }

    @Test
    void testValidBookAdd() {
        BookModel testValidBookAdd = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        Assertions.assertTrue(books.add(testValidBookAdd));
    }

    @Test
    void testInvalidBookAdd() {
        BookModel testInvalidBookAdd1 = new BookModel("", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        BookModel testInvalidBookAdd2 = new BookModel("Master i Margarita", "", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        BookModel testInvalidBookAdd3 = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "");
        Assertions.assertFalse(books.add(testInvalidBookAdd1));
        Assertions.assertFalse(books.add(testInvalidBookAdd2));
        Assertions.assertFalse(books.add(testInvalidBookAdd3));
    }

    @Test
    void testValidGetBook() {
        BookModel testValidGetBook = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        books.add(testValidGetBook);

        BookModel retrievedBook = books.getBook(x -> x.getName().equals("Master i Margarita"));
        Assertions.assertNotNull(retrievedBook);
    }
    @Test
    void testInvalidGetBook() {
        BookModel testInvalidGetBook = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        books.add(testInvalidGetBook);

        BookModel retrievedBook = books.getBook(x -> x.getName().equals("Master Margarita"));
        Assertions.assertNull(retrievedBook);
    }

    @Test
    void testValidGetBooks() {
        BookModel testValidGetBooks = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        books.add(testValidGetBooks);
        Assertions.assertTrue(books.getBooks(x -> x.getName().equals("Master i Margarita")).contains(testValidGetBooks));
    }


    @Test
    void testInvalidGetBooks() {
        BookModel testInvalidGetBooks = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        BookModel testInvalidGetBooks2 = new BookModel("Master i Margarita2", "M. Bulgakov2", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        books.add(testInvalidGetBooks);
        books.add(testInvalidGetBooks2);
        Assertions.assertFalse(books.getBooks(x -> x.getName().equals("Master Margarita")).contains(testInvalidGetBooks));
    }

    @Test
    void testValidBookRemove() {
        BookModel testValidBookRemove = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
        books.add(testValidBookRemove);

        books.remove(testValidBookRemove);
        BookModel removedBook = books.getBook(x -> x.getName().equals("Master i Margarita"));
        Assertions.assertNull(removedBook);
    }
//    @Test
//    void testInvalidBookRemove() {
//        BookModel testInvalidBookRemove = new BookModel("Master i Margarita", "M. Bulgakov", LocalDate.of(1960, 1, 1), "Жили были не тужили");
//        books.add(testInvalidBookRemove);
//
//        books.remove(testInvalidBookRemove);
//        BookModel removedBook = books.getBook(x -> x.getName().equals("Master i Margarita"));
//        Assertions.assertNull(removedBook);
//    }
//    

}
