import entities.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;


public class Main {

    /**
     * объявляем в классе, так как
     * может пригодиться
     * в разных методах
    */
    private static SessionFactory factory;

    public static void main(String[] args){

        try {
            factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable e){
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }

        Main instance = new Main();



        Integer emploeeID1 = instance.addEmployee("Zara", "Ali", 1000);
        Integer emploeeID2 = instance.addEmployee("Daisy", "Das", 5000);
        Integer emploeeID3 = instance.addEmployee("John", "Paul", 10000);

        instance.listEmployees();

        instance.updateEmployee(emploeeID1, 5000);

        instance.deleteEmployee(emploeeID2);

        instance.listEmployees();


        /**
         * нужно закрывать factory по окончании работы
         * иначе программа не завершиться
         * */
        factory.close();

    }

    public Integer addEmployee(String fname, String lname, int salary){

        Session session = factory.openSession();
        Transaction transaction = null;
        Integer employeeID = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary);
            /**
             * метод session.save() возвращает первичный ключ
             * (тип которого Сериализуем, т.е имплементит интерфейс Serializable)
             * записи объекта в таблице
             * */
            employeeID = (Integer) session.save(employee);
            transaction.commit();

        } catch (HibernateException e) {
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
            /**
             * указываем не имя таблицы, а имя класса,
             * указанное в Entities.hbm.xml
             */
            String hqlQuery = "FROM Employee";
            List employees = session.createQuery(hqlQuery).list();
            // либо попробовать через метод в JPA(нужно проверить): List employees = session.createQuery(hqlQuery).getResultList();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ){
                Employee employee = (Employee) iterator.next();
                System.out.println("FN: " + employee.getFirstName());
                System.out.println("LN: " + employee.getLastName());
                System.out.println("salary: " + employee.getSalary());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void updateEmployee(Integer employeeID, int salary){
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeID);
            employee.setSalary(salary);
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Integer employeeID){

        Session session = factory.openSession();
        Transaction transaction = null;

        try {


            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeID);
            session.delete(employee);
            transaction.commit();

        } catch (HibernateException e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}