package com.lmteixeira.personalfinances.domain.user;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void userCreatedWithEmailShouldHaveSameEmailAsStringRepresentation() {
        String userEmail = "test@gmail.com";
        User user = new User(userEmail);
        Assert.assertEquals(userEmail, user.toString());
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
