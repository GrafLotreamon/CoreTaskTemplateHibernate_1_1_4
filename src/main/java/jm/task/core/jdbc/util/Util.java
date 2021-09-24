package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/testmydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sesfact = null;

    public static SessionFactory getConnection() {

        try {
            Configuration config = new Configuration();
            Properties setting =  new Properties();
            setting.put(Environment.DRIVER, DRIVER);
            setting.put(Environment.URL, HOST);
            setting.put(Environment.USER, LOGIN);
            setting.put(Environment.PASS, PASSWORD);
            setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
            setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            setting.put(Environment.HBM2DDL_AUTO, "create-drop");

            config.setProperties(setting);
            config.addAnnotatedClass(User.class);


            sesfact = config.buildSessionFactory();
    } catch (Throwable e)

    {
        e.printStackTrace();
    }

        return sesfact;
    }
}