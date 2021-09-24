package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    private static final UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        User user1 = new User("Mile", "Harris", (byte) 25);
        User user2 = new User("Joun", "Harris", (byte) 15);

        userService.createUsersTable(); // метод работает (1)
        saveUserAindSout(user1); // метод работает (2)
        saveUserAindSout(user2); // метод работает (2)
        userService.removeUserById(2L); // метод работает (2.1)
        System.out.println(userService.getAllUsers()); // метод работает (3)
        userService.cleanUsersTable(); // метод работает (4);
        userService.dropUsersTable(); // метод работает (5)




    }

    private static void saveUserAindSout(User user1) {
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("Добавлен"+ user1.toString());// метод работает (2)
    }
}