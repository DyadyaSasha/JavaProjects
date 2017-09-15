package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ScalarandAggregateFunctions {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

          /** JPQA поддерживает следующие операции:

          SELECT ... FROM ...
          [WHERE ...]
          [GROUP BY ... [HAVING ...]]
          [ORDER BY ...]

           DELETE FROM ... [WHERE ...]

           UPDATE ... SET ... [WHERE ...]

           DELETE и UPDATE должны быть внутри
           manager.createTransaction().begin() и manager.createTransaction().commit()
           */

//          Scalar function
//          JPQL не поддерживает * в SELECT


            Query query = manager.createQuery("SELECT UPPER(e.ename) FROM Employee e");
            List<String> list = query.getResultList();

            for (String e : list){
                System.out.println(e);
            }

//          Aggregate function
            query = manager.createQuery("SELECT MAX(e.salary) from Employee e");
            Double result;
//          result = (Double) query.getResultList().get(0);
//          либо
            result = (Double) query.getSingleResult();
            System.out.println("Result: " + result);

            manager.close();
            factory.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(manager.isOpen()) manager.close();
            if (factory.isOpen()) factory.close();
        }


    }
}
