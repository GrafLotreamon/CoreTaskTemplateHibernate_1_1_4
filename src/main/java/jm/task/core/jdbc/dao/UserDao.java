package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {

    void createUsersTable(); // создание таблицы (1)
    void saveUser(String name, String lastName, byte age); // добавление (2)
    void removeUserById(long id); // удаление одного (2.1)
    List<User> getAllUsers(); // вывод всех (3)
    void cleanUsersTable(); // очистка базы (4)
    void dropUsersTable(); // удаление таблицы (5)

}