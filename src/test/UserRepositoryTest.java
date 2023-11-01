package test;

import models.UserModel;
import org.junit.Before;
import org.junit.Test;
import repositories.UserRepository;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testAdd() {
        UserModel user = new UserModel("test@example.com", "password123", "John", "Doe");
        userRepository.add(user);

        UserModel retrievedUser = userRepository.getUser(u -> u.getEmail().equals("test@example.com"));
        assertNotNull(retrievedUser);
    }

    @Test
    public void testRemove() {
        UserModel user = new UserModel("test@example.com", "password123", "John", "Doe");
        userRepository.add(user);

        userRepository.remove(user);

        UserModel removedUser = userRepository.getUser(u -> u.getEmail().equals("test@example.com"));
        assertNull(removedUser);
    }

    @Test
    public void testGetUser() {
        UserModel user = new UserModel("test@example.com", "password123", "John", "Doe");
        userRepository.add(user);

        UserModel retrievedUser = userRepository.getUser(u -> u.getEmail().equals("test@example.com"));
        assertNotNull(retrievedUser);
    }

    @Test
    public void testGetUsers() {
        UserModel user1 = new UserModel("user1@example.com", "password123", "Alice", "Johnson");
        UserModel user2 = new UserModel("user2@example.com", "password456", "Bob", "Smith");

        userRepository.add(user1);
        userRepository.add(user2);

        // Retrieve users with a specific condition (e.g., based on first name)
        assertTrue(userRepository.getUsers(u -> u.getFirstName().equals("Alice")).contains(user1));
    }

    @Test
    public void testEditUser() {
        UserModel user = new UserModel("test@example.com", "password123", "John", "Doe");
        userRepository.add(user);

        UserModel updatedUser = new UserModel("test@example.com", "newpassword", "Jane", "Smith");
        userRepository.editUser(updatedUser);

        UserModel editedUser = userRepository.getUser(u -> u.getEmail().equals("test@example.com"));
        assertNotNull(editedUser);
        assertEquals("newpassword", editedUser.getPassword());
        assertEquals("Jane", editedUser.getFirstName());
        assertEquals("Smith", editedUser.getLastName());
    }
}