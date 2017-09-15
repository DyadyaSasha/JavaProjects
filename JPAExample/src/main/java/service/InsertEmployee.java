package service;

import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InsertEmployee {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();


//          JPQA не поддерживает оператор INSERT
            manager.getTransaction().begin();

            Employee employee = new Employee();
            employee.setEid(1202);
            employee.setSalary(200);
            employee.setEname("Manisha");
            employee.setDeg("Technical Writer");

            Employee employee1 = new Employee();
            employee1.setEid(1203);
            employee1.setSalary(900);
            employee1.setEname("Masthanvali");
            employee1.setDeg("Proof Reader");

            Employee employee2 = new Employee();
            employee2.setEid(1204);
            employee2.setSalary(500);
            employee2.setEname("Satish");
            employee2.setDeg("Technical Writer");

            Employee employee3 = new Employee();
            employee3.setEid(1205);
            employee3.setSalary(100);
            employee3.setEname("Krishna");
            employee3.setDeg("Technical Writer");

            Employee employee4 = new Employee();
            employee4.setEid(1206);
            employee4.setSalary(50);
            employee4.setEname("Kiran");
            employee4.setDeg("Proof Reader");

            manager.persist(employee);
            manager.persist(employee1);
            manager.persist(employee2);
            manager.persist(employee3);
            manager.persist(employee4);

            manager.getTransaction().commit();

            manager.close();
            factory.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager.isOpen()) manager.close();
            if (factory.isOpen()) factory.close();
        }


    }
}
