package com.jwong.junit.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 同步集合
 * Created by jwong on 2017/10/24.
 */
public class CollectionModifyExceptionTest {

    public static void main(String[] args) {
        Collection<User> userList = new CopyOnWriteArrayList<User>();
//         new ArrayList<User>();
        userList.add(new User("用户1", "123456"));
        userList.add(new User("用户2", "123456"));
        userList.add(new User("用户3", "123456"));
        userList.add(new User("用户4", "123456"));
        userList.add(new User("用户5", "123456"));

        Iterator<User> userIterator = userList.iterator();

        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if ("用户1".equals(user.getName())) {
                userList.remove(user);
            } else {
                System.out.println(user);
            }
        }

        new ArrayList<>();
        Collections.synchronizedList(new ArrayList<>());

    }

    static class User {
        private String name;
        private String password;

        public User(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "用户名:" + this.name + ", 密码:" + this.password;
        }
    }

}
