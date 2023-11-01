package test;

import models.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.BookRepository;
import repositories.ElasticArray;
import repositories.Validate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;

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

}
