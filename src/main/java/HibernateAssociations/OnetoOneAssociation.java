package HibernateAssociations;

import model.onetoone.Designation;
import model.onetoone.Employee;
import org.hibernate.Session;

public class OnetoOneAssociation {

    public static void oneTooneAssociation(Session session) {
        session.beginTransaction();
        Employee emp = new Employee();
        emp.setEmployeeId(1);
        emp.setEmployeeName("Abhilash");
        emp.setSalary(10000);

        Designation designation = new Designation();
        designation.setDesignationId(1);
        designation.setDesignationName("Software Engineer");
        session.save(designation);

        emp.setDesignation(designation);
        session.save(emp);
        session.getTransaction().commit();
    }

}
