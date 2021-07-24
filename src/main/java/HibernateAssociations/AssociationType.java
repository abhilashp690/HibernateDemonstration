package HibernateAssociations;

import org.hibernate.Session;

import java.util.Scanner;

public class AssociationType {

    public static void associationType(Session session) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which association you want to check :- 1].OnetoOne \n2].OneToMany \n3].ManyToMany");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                OnetoOneAssociation.oneTooneAssociation(session);
                break;
        }
    }

}
