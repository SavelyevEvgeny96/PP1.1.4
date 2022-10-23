package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) {
       UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("efg","dfgb",(byte)45);
        System.out.println(userService.getAllUsers().toString());







//        for (int i=0;i<4;i++){
//        userService.saveUser("name%d".formatted(i),"lastname%d".formatted(i),(byte)(20+i));}
////        System.out.println("User с именем - name добавлен в баззу данных ");
////        List<User> userListd = userService.getAllUsers();
////        userListd.forEach(System.out::println);
////       userService.cleanUsersTable();
////       userService.dropUsersTable();

    }
}

