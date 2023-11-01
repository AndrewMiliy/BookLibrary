package test;

import models.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ElasticArray;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TestBookRepository {

    BookModel book;

    ElasticArray<BookModel> books = new ElasticArray<BookModel>();

    String startName = "Heart of the Dragon";
    String startAuthor = "Kirill Klevanskii";
    LocalDate date = LocalDate.parse("2000-10-10");
    ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
    Instant instant = zonedDateTime.toInstant();
    Date startPublishingDate = Date.from(instant);
    String startBookText = "This story begins from a ...";

    @BeforeEach
    void setUp() {
        book = new BookModel(startName, startAuthor, startPublishingDate, startBookText);
    }

    @Test
    void testBookAdd() {
    BookModel testBookAdd = new BookModel("Master i Margarita", "M. Bulgakov", new Date(), "Жили были не тужили")

    Assertions.assertEquals(validBookAdd, books.add(book));
    }

    @Test
    void testInvalidBookName() {
        String invalidBookName = "";
        book.setName(invalidBookName);
        Assertions.assertEquals(startName, book.getName());
    }

    @Test
    void testValidAuthor() {
        String validBookAuthor = "М. Булгаков";
        book.setAuthor(validBookAuthor);
        Assertions.assertEquals(validBookAuthor, book.getAuthor());
    }

    @Test
    void testInvalidAuthor() {
        String invalidBookAuthor = "";
        book.setAuthor(invalidBookAuthor);
        Assertions.assertEquals(startAuthor, book.getAuthor());
    }

    @Test
    void testValidBookText() {
        String validBookText = "Жили были, !не тужили!.";
        book.setBookText(validBookText);
        Assertions.assertEquals(validBookText, book.getBookText());
    }

    @Test
    void testInvalidBookText() {
        String invalidBookText = "";
        book.setBookText(invalidBookText);
        Assertions.assertEquals(startBookText, book.getBookText());
    }

    @Test
    void testValidPublishingDate() {
        LocalDate date = LocalDate.parse("1980-12-01");
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date validPublishingDate = Date.from(instant);
        book.setPublishingDate(validPublishingDate);
        Assertions.assertEquals(validPublishingDate, book.getPublishingDate());
    }

    @Test
    void testInvalidPublishingDate() {
        LocalDate date = LocalDate.parse("");
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date invalidPublishingDate = Date.from(instant);
        book.setPublishingDate(invalidPublishingDate);
        Assertions.assertEquals(startPublishingDate, book.getPublishingDate());
    }

}
