package presentation;

import models.BookModel;
import models.UserModel;
import models.UserRole;
import repositories.BookRepository;
import repositories.UserRepository;
import services.BookService;
import services.UserService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final BookService bookService;
    private final UserService userService;
    private final Scanner scanner;
    private UserModel currentUser;

    public ConsoleMenu(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    private void addBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();
        System.out.println("Enter book date (YYYY-MM-DD):");
        String dateInput = scanner.nextLine();
        Date date;
        try {
            date = Date.from(LocalDate.parse(dateInput, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return;
        }
        System.out.println("Enter book content:");
        String content = scanner.nextLine();
        BookModel book = new BookModel(title, author, date, content);
        bookService.addBook(book, currentUser);
        System.out.println("Book added successfully!");
    }

    private void removeBook() {
        System.out.println("Enter book ID to remove:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clearing buffer
        bookService.deleteBook(id, currentUser);
        System.out.println("Book removed successfully!");
    }

    private void editBook() {
        System.out.println("Enter new title:");
        String title = scanner.nextLine();
        System.out.println("Enter new author:");
        String author = scanner.nextLine();
        BookModel book = new BookModel(title, author);
        bookService.updateBook(book, currentUser);
        System.out.println("Book updated successfully!");
    }

    private void displayBookInfo() {
        System.out.println("Enter book ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clearing buffer
        BookModel book = bookService.getBookById(id);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void searchBook() {
        System.out.println("Enter book title to search:");
        String title = scanner.nextLine();
        List<BookModel> books = bookService.searchBook(title);
        for (BookModel book : books) {
            System.out.println(book);
        }
    }

    private void issueBookToUser() {
        System.out.println("Enter book ID to issue:");
        int bookId = scanner.nextInt();
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Clearing buffer
        boolean issued = bookService.issueBookToUser(bookId, userId);
        if (issued) {
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Could not issue book.");
        }
    }

    private void returnBook() {
        System.out.println("Enter book ID to return:");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Clearing buffer
        boolean returned = bookService.returnBook(bookId);
        if (returned) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Could not return book.");
        }
    }

    private void registerUser() {
        System.out.println("Enter firstName:");
        String firstName = scanner.nextLine();
        System.out.println("Enter lastName:");
        String lastName = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        UserModel user = new UserModel(firstName, lastName, email, password);
        if(userService.register(user))
        {
            System.out.println("User registered successfully!");
            currentUser = user;
        }
        else
        {
            System.out.println("Some error occurred.");
        }
    }

    private void authorizeUser() {
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        currentUser = userService.authorize(email, password);
        if (currentUser != null) {
            System.out.println("Logged in successfully!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void resetPassword() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        userService.recoverPassword(username, newPassword);
        System.out.println("Password reset successfully!");
    }

    private void listBooksForUser() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        List<BookModel> books = userService.getBooksForUser(currentUser);
        if(books.isEmpty())
        {
            System.out.println("No books found.");
            return;
        }
        for (BookModel book : books) {
            System.out.println(book);
        }
    }

    public void showMainMenu() {
        while (currentUser == null) {
            System.out.println("Please login or register first:");
            System.out.println("1. Register User");
            System.out.println("2. Authorize User");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // clearing the newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    authorizeUser();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Users");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // clearing the newline

            switch (choice) {
                case 1:
                    showBookMenu();
                    break;
                case 2:
                    showUserMenu();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showBookMenu() {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Display Book Info");
            System.out.println("5. Search Book");
            System.out.println("6. Issue Book to User");
            System.out.println("7. Return Book");
            System.out.println("8. Go back");
            int choice = scanner.nextInt();
            scanner.nextLine();  // clearing the newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    editBook();
                    break;
                case 4:
                    displayBookInfo();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    issueBookToUser();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Register User");
            System.out.println("2. Authorize User");
            System.out.println("3. Reset Password");
            System.out.println("4. List Books for User");
            System.out.println("5. Go back");
            int choice = scanner.nextInt();
            scanner.nextLine();  // clearing the newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    authorizeUser();
                    break;
                case 3:
                    resetPassword();
                    break;
                case 4:
                    listBooksForUser();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository, bookRepository);
        ConsoleMenu consoleMenu = new ConsoleMenu(bookService, userService);
        consoleMenu.showMainMenu();
    }
}