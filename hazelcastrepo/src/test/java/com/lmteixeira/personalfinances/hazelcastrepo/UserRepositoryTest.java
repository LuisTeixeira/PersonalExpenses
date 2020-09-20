package com.lmteixeira.personalfinances.hazelcastrepo;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.testdata.UserTestData;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserRepositoryTest {

    private UserRepository userRepository;
    private UserTestData userTestData;

    @Before
    public void setup() {
        this.userRepository = new HazelcastRepository();
        this.userTestData = UserTestData.getInstance();
    }

    @Test
    public void findUserByEmailShouldReturnUserAfterItBeingCreated() throws EntityNotFoundException {
        User user = userTestData.getUsers()[1];
        userRepository.create(user);
        User dbUser = userRepository.findUserByEmail(user.toString());
        Assert.assertNotNull(dbUser);
        Assert.assertEquals(user.toString(), dbUser.toString());
    }

    @Test
    public void findUserByEmailShouldReturnEntityNotFoundExceptionIfUserWasNotCreated() {
        boolean exceptionThrown = false;
        User user = userTestData.getUsers()[1];
        try {
            User userDb = userRepository.findUserByEmail(user.getEmail());
        } catch (EntityNotFoundException e) {
            exceptionThrown = true;
        }
        Assert.assertEquals(true, exceptionThrown);
    }

    @Test
    public void afterCreatingUsersAllShouldReturnAListWithTheNumberOfUsersCreated() {
        User[] testUsers = userTestData.getUsers();
        createUsers(testUsers);
        List<User> usersSaved = userRepository.all();
        Assert.assertEquals(testUsers.length, usersSaved.size());
    }


    @Test
    public void afterCreatingUsersFindUserByEmailShouldFindAllTheUsers() throws EntityNotFoundException {
        User[] testUsers = userTestData.getUsers();
        createUsers(testUsers);
        for (User user : testUsers) {
            User userDb = userRepository.findUserByEmail(user.getEmail());
            Assert.assertEquals(user.getEmail(), userDb.getEmail());
        }
    }

    @Test
    public void allShouldReturnAnEmptyListWhenNoUserWasCreated() {
        List<User> usersDb = userRepository.all();
        Assert.assertNotNull(usersDb);
        Assert.assertEquals(0, usersDb.size());
    }

    private void createUsers(User[] users) {
        for (User user : users) {
            userRepository.create(user);
        }
    }

}
