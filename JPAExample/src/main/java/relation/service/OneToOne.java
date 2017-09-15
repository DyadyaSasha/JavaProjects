package relation.service;

import relation.data.onetoone.DepEmployee;
import relation.data.onetoone.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * будет создано по одной таблице на каждый класс
 * */
public class OneToOne {

   public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
             manager = factory.createEntityManager();

            manager.getTransaction().begin();

            Department department = new Department();
            department.setName("Developmentqqq");

            manager.persist(department);

            DepEmployee employee = new DepEmployee();
            employee.setEname("Satish");
            employee.setSalary(45000.0);
            employee.setDeg("Technical Writer");
            employee.setDepartment(department);

            manager.persist(employee);

            manager.getTransaction().commit();

            manager.close();
            factory.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(manager.isOpen()) manager.close();
            if(factory.isOpen()) factory.close();
        }
    }
}
