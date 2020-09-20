package com.lmteixeira.personalfinances.hazelcastrepo;

import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.testdata.UserTestData;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setup() {
        this.userRepository = new HazelcastRepository();
    }

    @Test
    public void findUserByEmailShouldReturnUserAfterItBeingCreated() throws EntityNotFoundException {
        User user = UserTestData.getInstance().getUsers()[1];
        userRepository.create(user);
        User dbUser = userRepository.findUserByEmail(user.toString());
        Assert.assertNotNull(dbUser);
        Assert.assertEquals(user.toString(), dbUser.toString());
    }

}
