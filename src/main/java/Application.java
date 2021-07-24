
import CacheDemonstration.FirstLevelCache;
import HibernateAssociations.AssociationType;
import HibernateMethods.AdditionalMethods;
import HibernateMethods.LoadGet;
import HibernateMethods.SavePersist;
import HibernateUtils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Scanner;


public class Application {
    public static void main(String[] args){
        SessionFactory sf = null ;
        Session session = null;
        try {
            // Create session and sessionfactory
             sf = HibernateUtil.buildSessionFactory();
             session = sf.openSession();


            System.out.println("********************************************");
            System.out.println(Boolean.valueOf("true false true"));
            System.out.println("********************************************");

            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter operation choice - \n1].Get kafka Server Details \n2].Add Kafka Server Details \n3].Get All Kafka Details \n4].First Level Cache Demonstration \n5].Additiona Methods[Merge] \n6].Associations");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        session = sf.openSession();
                        System.out.println("Enter your choice - \n1.Get \n2].Load");
                        choice = sc.nextInt();
                        switch(choice) {
                            case 1:
                                LoadGet.getMethod(session);
                                break;
                            case 2:
                                LoadGet.loadMethod(session);
                                break;
                        }
                        break;

                    case 2:
                        session = sf.openSession();
                        System.out.println("\n1].Save \n2].Persist");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                SavePersist.SaveMethod(session);
                                break;
                            case 2:
                                SavePersist.PersistMethod(session);
                                break;
                        }
                        break;

                    case 3:
                        session = sf.openSession();
                        break;

                    case 4:
                        session = sf.openSession();
                        FirstLevelCache.firstLevelCache(session);
                        break;

                    case 5:
                        session = sf.openSession();
                        AdditionalMethods.additionalMethods(session);
                        break;

                    case 6:
                        session = sf.openSession();
                        AssociationType.associationType(session);
                        break;
                }
            }
        }
        catch(InterruptedException ex) {
            System.out.println("Some exception occured .....");
        }
       // Close session and sessionfactory
        finally {
            HibernateUtil.closeSession(session);
            HibernateUtil.destroySessionFactory(sf);
        }
    }
}
