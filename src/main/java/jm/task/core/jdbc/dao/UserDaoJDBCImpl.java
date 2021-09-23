package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {     //МЕТОД РАБОТАЕТ (1)

        try (Statement statement = connection.createStatement()) {
            statement.execute("Create table if not exists users " + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
                                            //МЕТОД РАБОТАЕТ (2)
    public void saveUser(String name, String lastName, byte age) { //МЕТОД РАБОТАЕТ (2)
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
           } catch (SQLException e) {
            e.printStackTrace();
        }
    }
                                      //МЕТОД РАБОТАЕТ (2.1)
    public void removeUserById(long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
            System.out.println("Удален user №"+ id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            System.out.println("Информация получена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public void cleanUsersTable() {     //МЕТОД  РАБОТАЕТ (4)
        try (Statement statement = connection.createStatement()) {
            statement.execute("Truncate table users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {     //МЕТОД РАБОТАЕТ (5)
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Drop table if exists users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }









//    public List<User> getAllUsers() {
//        try (Statement statement = connection.createStatement()) {
//                 statement.execute("                ");
//
//                 System.out.println();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("Не удалось очистить");
//
//            }
//        }


    }


