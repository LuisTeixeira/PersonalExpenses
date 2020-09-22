package com.lmteixeira.personalfinanaces.testdata;

import com.lmteixeira.personalfinances.testdata.UserTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTestDataTest {

    UserTestData userTestData;

    @Before
    public void setup() {
        userTestData = UserTestData.getInstance();
    }

    @Test
    public void getUsersShouldReturnAnArrayWithFourUsers() {
        Assert.assertEquals(4, userTestData.getUsers().length);
    }

}
