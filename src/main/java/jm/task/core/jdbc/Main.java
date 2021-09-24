package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    private static final UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Mile", "Gon", (byte )25);
        userService.saveUser("Nin", "Gon", (byte )25);
        userService.saveUser("Bob", "Gon", (byte )25);

        userService.removeUserById(1L);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}