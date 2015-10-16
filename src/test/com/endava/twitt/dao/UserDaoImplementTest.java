package com.endava.twitt.dao;

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesImplement;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by ibalanici on 16/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/appServlet/servlet-context-test.xml"})
public class UserDaoImplementTest {
    @Autowired
    private UserDaoImplement userDao;
//    @Autowired
    private User user;


    @Before
    public void setUp() {
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
    public void insertUserTest() {
        userDao.insertUser(user);
    }
}
