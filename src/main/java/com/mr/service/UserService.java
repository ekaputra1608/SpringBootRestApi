package com.mr.service;

import com.mr.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class UserService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findById(Long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(Long id) {

        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(User user) {
        return findByName(user.getName()) != null;
    }

    public void deleteAllUsers() {
        users.clear();
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000D));
        users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000D));
        users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000D));
        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000D));
        users.add(new User(counter.incrementAndGet(), "John", 20, 1000D));
        return users;
    }

}
