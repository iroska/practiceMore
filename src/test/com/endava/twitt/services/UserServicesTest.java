package com.endava.twitt.services;

import com.endava.twitt.models.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ibalanici on 19/10/2015.
 */
public class UserServicesTest {
    private UserServicesInterface userService;
    private User user;

    private static ClassPathXmlApplicationContext applicationContext;

    @BeforeClass
    public static void setUpOnce() {
        applicationContext = new ClassPathXmlApplicationContext("/spring/appServlet/servlet-context-test.xml");
    }

    @Before
    public void setUp() {
        userService = (UserServicesInterface) applicationContext.getBean("userService");
        user = new User();
        user.setFirstName("Igor-1");
        user.setLastName("Balanici-1");
        user.setEmail("igor.balanicii@gmail.com");
        user.setPassword("11111111");
        user.setRole("ROLE_USER");
    }

    @Test
    public void crudUserServicesTest() {
        userService.insertUser(user);
        assertEquals(userService.getUserByName("igor.balanicii@gmail.com").getEmail(), user.getEmail());

        user.setFirstName("Vasea");
        userService.updateUser(user);
        assertEquals(userService.getUserByName("igor.balanicii@gmail.com").getFirstName(), user.getFirstName());

        userService.deleteUser("igor.balanicii@gmail.com");
        assertNull(userService.getUserByName("igor.balanicii@gmail.com"));

    }

}
