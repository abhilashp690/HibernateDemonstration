package HibernateMethods;

import HibernateUtils.HibernateUtil;
import model.KafkaProperties;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.util.Scanner;

public class SavePersist {
    static Scanner sc = new Scanner(System.in);

    public static void SaveMethod(Session session) {
        Session ss = HibernateUtil.buildSessionFactory().openSession();
        Transaction tx = ss.beginTransaction();
        try {
            KafkaProperties kafkaProps = new KafkaProperties();
            System.out.println("Enter min insync replicas ..");
            int insyncreplica = sc.nextInt();
            kafkaProps.setMaxinsyncreplicas(insyncreplica);
            kafkaProps.setBootStrapServer("10.133.73.92:2181");
            System.out.println("Enter kafka props id ..");
            int kafkaId = sc.nextInt();
            kafkaProps.setKafkaId(kafkaId);


            ss.save(kafkaProps);


            if(insyncreplica == 10)
                throw new NullPointerException("Gotcha");

            tx.commit();
            ss.close();
        }
        catch (Exception e) {
            System.out.println("Exception caught hahahaha " + e);
        } finally {
            ss.close();
        }
    }


    public static void PersistMethod(Session session) {
        try {
            Transaction tx = session.beginTransaction();
            KafkaProperties kafkaProps = new KafkaProperties();
            System.out.println("Enter min insync replicas ..");
            int insyncreplica = sc.nextInt();
            System.out.println("Enter kafka props id ..");
            int kafkaId = sc.nextInt();
            kafkaProps.setMaxinsyncreplicas(insyncreplica);
            kafkaProps.setBootStrapServer("10.133.73.92:2181");
            kafkaProps.setKafkaId(kafkaId);
            session.persist(kafkaProps);


            session.evict(kafkaProps);
            kafkaProps.setBootStrapServer("bootstrap list using - persist");
            session.persist(kafkaProps);

            tx.commit();
        }
        catch(Exception e){
            System.out.println("*************************** Persist Operation cannot be processed for detached entity ....**************************");
        }
    }
}
