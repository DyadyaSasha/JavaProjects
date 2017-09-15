import entities.Employee;
import entities.Licenseempl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class HibSet {

    private static SessionFactory factory;

    public static void main(String[] args){

        try {
            factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable e){
            System.out.println("failed to create sessionfactory object." + e);
            throw new ExceptionInInitializerError(e);
        }

        HibSet hibSet = new HibSet();

        HashSet<Licenseempl> set1 = new HashSet<>();

        set1.add(new Licenseempl("MCA"));
        set1.add(new Licenseempl("MBA"));
        set1.add(new Licenseempl("PMP"));

        Integer empID1 = hibSet.addEmployee("Manoj", "Kumar", 4000, set1);

        HashSet<Licenseempl> set2 = new HashSet<>();
        set2.add(new Licenseempl("BCA"));
        set2.add(new Licenseempl("BA"));

        Integer empID2 = hibSet.addEmployee("Dilip", "Kumar", 3000, set2);

        hibSet.listEmployees();

        hibSet.updateemployee(empID1, 5000);

        hibSet.deleteEmployee(empID2);

        hibSet.listEmployees();

        factory.close();

    }


    private Integer addEmployee(String fname, String lname, int salary, Set set){

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer employeeID = null;

        try{
            transaction = session.beginTransaction();

            Employee employee = new Employee(fname, lname, salary);
            employee.setCertificates(set);
            employeeID = (Integer) session.save(employee);

            transaction.commit();

        } catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employeeID;
    }

    public void listEmployees(){
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String hqlQuery = "FROM Employee";
            List employees = session.createQuery(hqlQuery).getResultList();
            for(Iterator iterator = employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
                Set certificates = employee.getCertificates();
                for (Iterator iterator1 = certificates.iterator(); iterator1.hasNext();){
                    Licenseempl сertificate = (Licenseempl) iterator1.next();
                    System.out.println("Certificate: " + сertificate.getName());
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private void updateemployee(Integer empID1, int salary) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empID1);
            employee.setSalary(salary);
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void deleteEmployee(Integer empID2) {
        Session session = factory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, empID2);
            session.delete(employee);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
