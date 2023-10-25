package com.example.login;

import com.example.login.dao.UserDao;
import com.example.login.model.User;
import com.example.login.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        // Create a sample list of users
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "Doe", "john@example.com", "1234567890", "1990-01-01", "password"));

        // Mock the behavior of the userDao using when/thenReturn
        when(userDao.findAll()).thenReturn(users);

        // Call the service method
        List<User> response = userService.getUsers();

        // Verify the response
        verify(userDao, times(1)).findAll();
        assertEquals(users, response);
    }

    @Test
    public void testAddUser() {
        // Create a sample user
        User newUser = new User(2, "Jane", "Doe", "jane@example.com", "9876543210", "1995-02-02", "newPassword");

        // Mock the behavior of the userDao using when/thenReturn
        when(userDao.save(newUser)).thenReturn(newUser);

        // Call the service method
        User response = userService.addUser(newUser);

        // Verify the response
        verify(userDao, times(1)).save(newUser);
        assertEquals(newUser, response);
    }

    @Test
    public void testChangePassword() {
        // Create a sample user
        User user = new User(3, "Alice", "Smith", "alice@example.com", "9876543210", "1995-03-03", "oldPassword");

        // Mock the behavior of the userDao using when/thenReturn
        when(userDao.save(user)).thenReturn(user);

        // Call the service method
        User response = userService.changePassword(user);

        // Verify the response
        verify(userDao, times(1)).save(user);
        assertEquals(user, response);
    }
}

