package service;

import data.Employee;

import javax.management.OperationsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DeleteEmployee {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try{

            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

//            Через язык запросов(каждое изменение в базе должно быть между:
//            manager.getTransaction().begin() и manager.getTransaction().commit()):
//            manager.getTransaction().begin();
//            Query query1 = manager.createQuery("DELETE  from Employee e where e.ename = \"SASHA\"");
//            int reult = query1.executeUpdate();
//            System.out.println(reult);
//            manager.getTransaction().commit();

            Employee employee = manager.find(Employee.class, 1201);

            manager.getTransaction().begin();
            manager.remove(employee);
            manager.getTransaction().commit();

//            manager.close();
//            factory.close();

        } catch (Exception e){
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
