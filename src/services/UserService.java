package services;

import models.BookModel;
import models.UserModel;
import models.UserRole;
import repositories.BookRepository;
import repositories.ElasticArray;
import repositories.UserRepository;
import repositories.HashedPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService {
    private static UserRepository userRepository;
    private BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public static UserModel getUser(Predicate<UserModel> predicate) {
        return userRepository.getUser(predicate);
    }

    // Редактировать роль пользователя (только для админа)
    public void setUserRole(UserModel user, UserRole role, UserModel admin) {
        if (admin.getUserRole() == UserRole.ADMIN) {
            user.setUserRole(role);
        } else {
            System.out.println("Only admin can edit user roles.");
        }
    }


    // Регистрация
    public void register(String email, String firstName, String lastName, String password) {
        UserModel newUser = new UserModel(email, HashedPassword.hashPassword(password), firstName, lastName);
        userRepository.add(newUser);
    }

    // Авторизация
    public UserModel authorize(String email, String password) {
        UserModel user = userRepository.getUser(u -> u.getEmail().equals(email));
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Восстановить пароль (здесь просто установим новый пароль для простоты)
    public boolean recoverPassword(String email, String newPassword) {
        UserModel user = userRepository.getUser(u -> u.getEmail().equals(email));
        if (user != null) {
            user.setPassword(HashedPassword.hashPassword(newPassword));
            return true;
        }
        return false;
    }

    public ElasticArray<BookModel> getBooksForUser(UserModel user) {
        ElasticArray<Integer> bookIds = user.getBookIds();
        if (bookIds == null) {
            return new ElasticArray<>();
        }

        ElasticArray<BookModel> books = new ElasticArray<>();
        for (int i = 0; i < bookIds.size(); i++) {
            int finalI = i;
            BookModel book = bookRepository.getBook(b -> b.getId() == bookIds.get(finalI));
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }

    public boolean register(UserModel user) {
        if(userRepository.add(user))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void saveUsers() {
        userRepository.saveUsers();
    }
}