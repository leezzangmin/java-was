package db;

import com.google.common.collect.Maps;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDataBase {
    private UserDataBase() {
    }

    private static final Logger log = LoggerFactory.getLogger(UserDataBase.class);

    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        if (isDuplicatedUser(user)){
            return;
        }
        log.debug("user : {}", user);
        users.put(user.getUserId(), user);
    }

    private static boolean isDuplicatedUser(User user) {
        if (users.containsKey(user.getUserId())){
            log.debug("이미 회원가입된 회원입니다");
            return true;
        }
        return false;
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static List<User> findAll() {
        return new ArrayList<>(users.values());
    }

}
