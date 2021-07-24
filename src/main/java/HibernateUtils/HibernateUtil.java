package HibernateUtils;

import model.KafkaProperties;
import model.onetoone.Designation;
import model.onetoone.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {


    public static SessionFactory buildSessionFactory() {
        Configuration cfg = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL,"jdbc:mysql://localhost:3306/hibernatedb");
        settings.put(Environment.USER , "root");
        settings.put(Environment.PASS , "root01");
        settings.put(Environment.DIALECT , "org.hibernate.dialect.MySQL5Dialect");
        //settings.put(Environment.SHOW_SQL , true);
        //settings.put(Environment.FORMAT_SQL , true);

        // This setting will always drop the table , will create new on startup
        // settings.put(Environment.HBM2DDL_AUTO , "create-drop");

        settings.put(Environment.HBM2DDL_AUTO , "create-drop");

        cfg.setProperties(settings);
        cfg.addAnnotatedClass(KafkaProperties.class).addAnnotatedClass(Employee.class)
        .addAnnotatedClass(Designation.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().
                applySettings(cfg.getProperties()).build();
        SessionFactory sf = cfg.buildSessionFactory(sr);
        return sf;
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static void destroySessionFactory(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
}
