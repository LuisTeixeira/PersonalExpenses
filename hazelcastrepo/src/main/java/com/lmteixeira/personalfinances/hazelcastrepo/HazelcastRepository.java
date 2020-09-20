package com.lmteixeira.personalfinances.hazelcastrepo;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.lmteixeira.personalfinances.domain.user.User;
import com.lmteixeira.personalfinances.hazelcastrepo.model.HazelcastUser;
import com.lmteixeira.personalfinances.usecases.interfaces.UserRepository;
import com.lmteixeira.personalfinances.usecases.interfaces.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class HazelcastRepository implements UserRepository {

    private static final HazelcastInstance HAZELCAST = Hazelcast.getInstance();
    private static final String MAP_NAME = "user";

    @Override
    public void create(User user) {
        HazelcastUser userDb = HazelcastUser.fromUser(user);
        IMap map = HAZELCAST.getMap(MAP_NAME);
        map.put(userDb.getEmail(), userDb);
    }

    @Override
    public List<User> all() {
        IMap<String, HazelcastUser> map = HAZELCAST.getMap(MAP_NAME);
        return map.values().stream().map(HazelcastUser::toUser).collect(Collectors.toList());
    }

    @Override
    public User findUserByEmail(String email) throws EntityNotFoundException {
        IMap<String, HazelcastUser> map = HAZELCAST.getMap(MAP_NAME);
        HazelcastUser userDb = map.get(email);
        if (userDb == null) {
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
        return userDb.toUser();
    }

}
