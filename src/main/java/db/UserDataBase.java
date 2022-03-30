package db;

import com.google.common.collect.Maps;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDataBase {
    private UserDataBase() {
    }

    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        isDuplicatedUser(user);
        users.put(user.getUserId(), user);
    }

    private static void isDuplicatedUser(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static List<User> findAll() {
        return new ArrayList<>(users.values());
    }

}
