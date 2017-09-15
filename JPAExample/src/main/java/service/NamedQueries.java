package service;

import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class NamedQueries {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            Query query = manager.createNamedQuery("find employee by id");
            query.setParameter("id", 1204);
            List<Employee> list = query.getResultList();
            for (Employee e : list){
                System.out.println(e.getEid() + ": \n\t" + e.getEname());
            }
//        либо
//        System.out.println(query.getSingleResult());

            manager.close();
            factory.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
