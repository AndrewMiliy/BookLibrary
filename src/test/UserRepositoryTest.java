package test;
import models.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import repositories.UserRepository;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Predicate;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testAddValidUser() {
        UserModel user = new UserModel("John", "Doe", "john.doe@example.com", "StrongPassword123+");
        assertTrue(userRepository.add(user));
        assertEquals(1, userRepository.getUserCount());
    }

    @Test
    public void testAddInvalidUser() {
        UserModel user = new UserModel("John", "Doe", "invalid-email", "weak");
        assertFalse(userRepository.add(user));
        assertEquals(0, userRepository.getUserCount());
    }

    @Test
    public void testRemoveUser() {
        UserModel user = new UserModel("John", "Doe", "john.doe@example.com", "StrongPassword123+");
        userRepository.add(user);
        userRepository.remove(user);
        assertEquals(0, userRepository.getUserCount());
    }

    @Test
    public void testGetUser() {
        UserModel user = new UserModel("John", "Doe", "john.doe@example.com", "StrongPassword123+");
        userRepository.add(user);

        Predicate<UserModel> predicate = u -> u.getEmail().equals("john.doe@example.com");
        UserModel retrievedUser = userRepository.getUser(predicate);

        assertNotNull(retrievedUser);
        assertEquals("John", retrievedUser.getFirstName());
    }

    @Test
    public void testGetUsers() {
        UserModel user1 = new UserModel("John", "Doe", "john.doe@example.com", "StrongPassword123+");
        UserModel user2 = new UserModel("Jane", "Smith", "jane.smith@example.com", "AnotherPassword123+");

        userRepository.add(user1);
        userRepository.add(user2);

        Predicate<UserModel> predicate = u -> !u.getEmail().isEmpty();
        List<UserModel> users = userRepository.getUsers(predicate);

        assertEquals(2, users.size());
    }

    @Test
    public void testEditUserValid() {
        UserModel user = new UserModel("John", "Doe", "john.doe@example.com", "StrongPassword123+");
        userRepository.add(user);

        //UserModel updatedUser = new UserModel("Jane", "Smith", "jane.smith@example.com", "NewPassword123+");
         user.setFirstName("Jane");
         user.setLastName("Smith");
         user.setEmail("jane.smith@example.com");
         user.setPassword("NewPassword123+");
         assertTrue(userRepository.editUser(user));

        UserModel retrievedUser = userRepository.getUser(x->x.getId()==user.getId());
        assertEquals("Jane", retrievedUser.getFirstName());
        assertEquals("Smith", retrievedUser.getLastName());
        assertEquals("jane.smith@example.com", retrievedUser.getEmail());
        assertEquals("NewPassword123+", retrievedUser.getPassword());
    }

    @Test
    public void testValidSafeUsers() {
        UserModel user = new UserModel("John", "Doe", "asd@asd.asd", "Qwerty1+");
        UserModel user1 = new UserModel("Johny", "Sins", "johnysins777@ww.mma", "Asdrft3#");
        userRepository.add(user);
        userRepository.add(user1);
        userRepository.saveUsers();
        userRepository.loadUsers();

        user = userRepository.getUser(x -> x.getEmail().equals("asd@asd.asd"));
        user1 = userRepository.getUser(x -> x.getEmail().equals("johnysins777@ww.mma"));
        Assertions.assertTrue(user.getFirstName().equals("John") && user.getLastName().equals("Doe") && user.getEmail().equals("asd@asd.asd") && user.getPassword().equals("Qwerty1+"));
        Assertions.assertTrue(user1.getFirstName().equals("Johny") && user1.getLastName().equals("Sins") && user1.getEmail().equals("johnysins777@ww.mma") && user1.getPassword().equals("Asdrft3#"));
    }
}