package service;

import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateEmployee {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {

            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();


//          JPQA не поддерживает оператор INSERT

            manager.getTransaction().begin();

            Employee employee = new Employee();

            employee.setEid(1201);
            employee.setEname("SASHA");
            employee.setSalary(400);
            employee.setDeg("TE");

            manager.persist(employee);

            manager.getTransaction().commit();

            manager.close();
            factory.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            так выведет ошибку:IllegalStateException: Attempting to execute an operation on a closed EntityManager.
//            if (manager != null) manager.close();
//            if (factory != null) factory.close();

            if(manager.isOpen()) manager.close();
            if (factory.isOpen()) factory.close();

        }
    }
}
