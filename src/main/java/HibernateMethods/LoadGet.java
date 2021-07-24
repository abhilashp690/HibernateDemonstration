package HibernateMethods;

import HibernateUtils.HibernateUtil;
import model.KafkaProperties;
import org.hibernate.Session;

import java.util.Scanner;

public class LoadGet {
    static Scanner sc = new Scanner(System.in);

    public static void getMethod(Session session) {
        System.out.println("Enter the server Id whose details needs to be fetched...");
        int serverId = sc.nextInt();

        KafkaProperties kafkaServerDetails = session.get(KafkaProperties.class , serverId);
        System.out.println("Kafka Server Details are :- " + kafkaServerDetails.getKafkaId());
        System.out.println("session.get will always hit db , even though other properties except id is not even required.");
        System.out.println("Another difference is :- load will throw exception if id does not exists whereas get will return null");
        System.out.println("Kafka Details are fetched using session.load , using load it will not hit db until actual data is not requested , instead it returns a proxy oject");
        HibernateUtil.closeSession(session);
    }

    public static void loadMethod(Session session) {
        System.out.println("Enter the server Id whose details needs to be fetched...");
        int serverId = sc.nextInt();

        KafkaProperties kafkaServerDetails = session.load(KafkaProperties.class , serverId);
        System.out.println("Kafka Server Details are :- " + kafkaServerDetails.getKafkaId());
        System.out.println("session.get will always hit db , even though other properties except id is not even required.");
        System.out.println("Another difference is :- load will throw exception if id does not exists whereas get will return null");
        System.out.println("Kafka Details are fetched using session.load , using load it will not hit db until actual data is not requested , instead it returns a proxy oject");
        HibernateUtil.closeSession(session);
    }
}
