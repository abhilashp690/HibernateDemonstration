package CacheDemonstration;

import HibernateUtils.HibernateUtil;
import model.KafkaProperties;
import org.hibernate.Session;

import java.util.Scanner;

public class FirstLevelCache {

    static Scanner sc = new Scanner(System.in);

    public static void firstLevelCache(Session session) throws InterruptedException{
        System.out.println("Enter the server Id for whom details are needed...");
        int serverId = sc.nextInt();

        System.out.println("Fetching details of kafka server with id - " + serverId);
        KafkaProperties serverDetails = session.get(KafkaProperties.class , serverId);
        System.out.println("First level cache scope is within session , so when get method is triggered twice/more within same session , it will not hit db instead will fetch data from first level cache.");
        System.out.println("Fetched Deatils are - " + serverDetails);
        Thread.sleep(2000);
        serverDetails = session.get(KafkaProperties.class , serverId);
        System.out.println("As you can see here , only 1 db call is triggered.");

        Thread.sleep(1000);
        System.out.println("Now entity can also be removed from session using any of these 2 methods.\n1].session.evict(entity) \n2].session.remove(entity) \n3].session.clear(removes all entities)");
        System.out.println("Enter your choice - ");

        int sessionRemovalEntity = sc.nextInt();
        switch (sessionRemovalEntity) {
            case 1:
                session.evict(serverDetails);
                serverDetails = session.get(KafkaProperties.class , serverId);
                System.out.println("Db query now made , as we explicitly removed this instance from cache using evict, now after this query it is again stored in cache.");
                break;

            case 2:
                session.remove(serverDetails);
                serverDetails = session.get(KafkaProperties.class , serverId);
                System.out.println("Db query now made , as we explicitly removed this instance from cache using remove, now after this query it is again stored in cache.");
                break;

            case 3:
                session.clear();
                serverDetails = session.get(KafkaProperties.class , serverId);
                System.out.println("Db query now made , as we explicitly removed this instance from cache using clear.Clear removes all entities from cache , now after this query it is again stored in cache.");
                break;
        }

        HibernateUtil.closeSession(session);
    }

}
