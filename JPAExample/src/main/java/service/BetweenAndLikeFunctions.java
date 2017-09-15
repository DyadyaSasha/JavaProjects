package service;

import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class BetweenAndLikeFunctions {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

//      Between
            Query query = manager.createQuery("SELECT e FROM Employee e " +
                    "WHERE e.salary BETWEEN 400 AND 100000");

//      Здесь можно не приводить явно к List<Employee>, так как в строке запроса уже указан тип возращаемого значения
            List<Employee> list = (List<Employee>) query.getResultList();

            for (Employee e : list){
                System.out.println(e.getEid() + ":\n\t" + e.getSalary());
            }

//      Like
            query = manager.createQuery("SELECT e FROM Employee e " +
                    "WHERE e.ename LIKE 'M%'");
            list = (List<Employee>) query.getResultList();

            for (Employee e : list){
                System.out.println(e.getEid() + ":\n\t" + e.getEname());
            }

//      Order by
            query = manager.createQuery("SELECT e FROM Employee e " +
                    "ORDER BY e.ename ASC");
            list =  query.getResultList();

            for (Employee e : list){
                System.out.println(e.getEid() + ":\n\t" + e.getEname());
            }

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
