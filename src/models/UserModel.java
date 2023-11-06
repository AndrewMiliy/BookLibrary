package models;

import repositories.IdCounter;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private UserRole userRole;

    // Список ID книг, которые у пользователя
    private List<Integer> bookIds;

    private int id;

    public UserModel(String firstName, String lastName, String email, String password) {
        this.setId(IdCounter.Instance.getNextId());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void addBookId(int bookId) {
        bookIds.add(bookId);
    }

    public void removeBookId(int bookId) {
        bookIds.remove(Integer.valueOf(bookId));
    }

    public void setId(int id) {
        this.id = id;
    }
}
