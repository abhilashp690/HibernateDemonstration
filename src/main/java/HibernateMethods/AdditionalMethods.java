package HibernateMethods;

import model.KafkaProperties;
import org.hibernate.Session;

import java.util.Scanner;

public class AdditionalMethods {
    static Scanner sc = new Scanner(System.in);

    public static void additionalMethods(Session session) throws InterruptedException {
        System.out.println("Enter your choice \n1].Merge \n2].update \n3].saveOrUpdate");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                mergeDemo(session);
                break;

            case 2:
                updateDemo(session);
                break;

            case 3:
                saveOrUpdate(session);
                break;
        }
    }

    private static void saveOrUpdate(Session session) throws InterruptedException {
        session.beginTransaction();

        System.out.println("Enter min insync replicas ..");
        int replicaCount = sc.nextInt();
        KafkaProperties kafkaProps = new KafkaProperties();
        kafkaProps.setMaxinsyncreplicas(replicaCount);
        kafkaProps.setBootStrapServer("bootstrap server list :- before saveOrupdate");
        session.saveOrUpdate(kafkaProps);

        session.evict(kafkaProps);

        kafkaProps.setBootStrapServer("bootstrap server list :- after saveOrupdate");
        session.saveOrUpdate(kafkaProps);

        session.getTransaction().commit();
        System.out.println("Update Method when called on transient state -> results in exception . Update method must be called on detached instance.");
        Thread.sleep(3000);
    }

    private static void updateDemo(Session session) throws InterruptedException {
        session.beginTransaction();

        System.out.println("Enter min insync replicas ..");
        int replicaCount = sc.nextInt();
        KafkaProperties kafkaProps = new KafkaProperties();
        kafkaProps.setMaxinsyncreplicas(replicaCount);
        kafkaProps.setBootStrapServer("bootstrap server list :- before update");
        session.save(kafkaProps);

        session.evict(kafkaProps);

        kafkaProps.setBootStrapServer("bootstrap server list :- after update");
        session.update(kafkaProps);

        session.getTransaction().commit();
        System.out.println("Update Method when called on transient state -> results in exception . Update method must be called on detached instance.");
        Thread.sleep(3000);
    }

    private static void mergeDemo(Session session) {
        session.beginTransaction();

        System.out.println("Enter min insync replicas ..");
        int replicaCount = sc.nextInt();
        KafkaProperties kafkaProps = new KafkaProperties();
        kafkaProps.setMaxinsyncreplicas(replicaCount);
        kafkaProps.setBootStrapServer("bootstrap server list :- before merge");
        session.save(kafkaProps);

        session.evict(kafkaProps);

        kafkaProps.setBootStrapServer("bootstrap server list :- after merge");
        KafkaProperties kafkaMergedInstance = (KafkaProperties) session.merge(kafkaProps);
        System.out.println("Merged Kafka Instance details are - "+kafkaMergedInstance);

        session.getTransaction().commit();
    }
}
