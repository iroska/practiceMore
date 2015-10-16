package com.endava.twitt.dao;

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesImplement;
import com.endava.twitt.services.UserServicesInterface;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by ibalanici on 16/10/2015.
 */
public class UserDaoImplementTest {
    public static UserDaoInterface userDao;
    public static User user;


    @Before
    public void setUp() {
        userDao = new UserDaoImplement();
        user = new User();
        user.setFirstName("Igor-1");
        user.setLastName("Balanici-1");
        user.setEmail("igor.balanici@gmail.com");
        user.setPassword("111111");
        user.setRole("ROLE_USER");
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testFirst() {
        assertTrue(true);
    }

    @Test
    public void insertUserTest() {
        userDao.insertUser(user);
    }
}
