package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Eleri", LocalDate.now().minusYears(35), null));
        users.add(new User(++usersCount, "Alexander", LocalDate.now().minusYears(25), null));
        users.add(new User(++usersCount, "Lisandra", LocalDate.now().minusYears(15), null));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteUserById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
}
