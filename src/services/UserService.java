package services;

import models.BookModel;
import models.UserModel;
import models.UserRole;
import repositories.BookRepository;
import repositories.UserRepository;
import repositories.HashedPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    // Редактировать роль пользователя (только для админа)
    public void setUserRole(UserModel user, UserRole role, UserModel admin) {
        if (admin.getUserRole() == UserRole.Admin) {
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
        if (user != null && user.getPassword().equals(HashedPassword.hashPassword(password))) {
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

    public List<BookModel> getBooksForUser(UserModel user) {
        List<Integer> bookIds = user.getBookIds();
        if (bookIds == null) {
            return new ArrayList<>();
        }
        return bookIds.stream()
                .map(bookId -> bookRepository.getBook(b -> b.getId() == bookId))
                .filter(book -> book != null)
                .collect(Collectors.toList());
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
