package repository.tests;
import model.User;
import model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepositoryImpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author olgakharina
 * @date 20.11.24
 */
class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    void testAddUser() {
        User newUser = userRepository.addUser("TestUser", "testuser@example.com", "password123");

        assertNotNull(newUser);
        assertEquals("TestUser", newUser.getName());
        assertEquals("testuser@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @Test
    void testIsEmailExists() {
        assertTrue(userRepository.isEmailExists("alexe@example.com"));
        assertFalse(userRepository.isEmailExists("nonexistent@example.com"));
    }

    @Test
    void testGetUserByName() {
        List<User> users = userRepository.getUserByName("Alex");

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertEquals("Alex", users.get(0).getName());
    }

    @Test
    void testGetUserByEmail() {
        User user = userRepository.getUserByEmail("alexe@example.com");

        assertNotNull(user);
        assertEquals("Alex", user.getName());

        User nonExistentUser = userRepository.getUserByEmail("nonexistent@example.com");
        assertNull(nonExistentUser);
    }

    @Test
    void testGetUserByID() {
        User user = userRepository.getUserByID(1);

        assertNotNull(user);
        assertEquals("Alex", user.getName());

        User nonExistentUser = userRepository.getUserByID(99);
        assertNull(nonExistentUser);
    }

    @Test
    void testDeleteUser() {
        assertTrue(userRepository.deleteUser(1));

        // Проверяем, что пользователь удален
        User deletedUser = userRepository.getUserByID(1);
        assertNull(deletedUser);

        // Попытка удалить несуществующего пользователя
        assertFalse(userRepository.deleteUser(99));
    }

}