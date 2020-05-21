package com.lmteixeira.personalfinances.domain.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private static final String USER_EMAIL = "test@gmail.com";

    private User user;

    @Before
    public void setup() {
        user = new User(USER_EMAIL);
    }

    @Test
    public void userCreatedWithEmailShouldHaveSameEmailAsStringRepresentation() {
        Assert.assertEquals(USER_EMAIL, user.toString());
    }

    @Test
    public void userCreateWithEmailShouldThrowExceptionWhenANonValidEmailIsUsed() {
        String[] invalidEmails = new String[]{"test", "test@", "test@gmail", "@gmail", "@gmail.com"};

        for (int i = 0; i < invalidEmails.length; i++) {
            boolean exceptionThrown = false;
            try {
                User user = new User(invalidEmails[i]);
            } catch (Exception e) {
                exceptionThrown = true;
            }
            Assert.assertTrue("Invalid email with index " + i + " failed", exceptionThrown);
        }
    }


}
