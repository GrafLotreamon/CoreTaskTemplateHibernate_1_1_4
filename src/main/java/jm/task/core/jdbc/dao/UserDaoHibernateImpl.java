package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session sesson = sessionFactory.openSession();
        Transaction transaction =  sesson.beginTransaction();
        sesson.createSQLQuery("CREATE TABLE IF NOT EXISTS testmydb" +
                "(id mediumint not null auto_increment, name VARCHAR(50)," +
                "lastname VARCHAR (50), " +
                "age tinyint,"+
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        sesson.close();
        System.out.println("Таблица создана");


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        System.out.println("Добавлен новые User:" + name);
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        System.out.println("Удалён User c id "+ id);
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        List <User>  list = new  ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        transaction.commit();
        System.out.println(list.toString());
        session.close();
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <User> listclean = session.createCriteria(User.class).list();
        for(Object o : listclean) {
            session.delete(o);
        }
        session.getTransaction().commit();
        System.out.println("Таблица очищена");
        }





    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("Drop table if exists testmydb").executeUpdate();
        transaction.commit();
        System.out.println("Таблица удалена");
        session.close();




    }
}