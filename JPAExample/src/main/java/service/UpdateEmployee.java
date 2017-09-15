package service;

import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UpdateEmployee {
    
    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {

            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();

            Employee employee = manager.find(Employee.class, 1201);

            System.out.println(employee);

            employee.setSalary(400000000);
            manager.getTransaction().commit();

            System.out.println(employee);

//            manager.close();
//            factory.close();

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
