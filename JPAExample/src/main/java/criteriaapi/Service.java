package criteriaapi;


import data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Criteria API - запросы к базе данных, так же как и JPQL, но в объектном стиле
 * (что позволяет ловить ошибки (например с типами) на этапе компиляциии)
 * */
public class Service {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {

            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            CriteriaBuilder builder = manager.getCriteriaBuilder();

            // создаём объект запроса query, который будем настраивать
            // (добавлять нужные критерии), чтобы получить нужный результат
            // CriteriaQuery<T> T - тип возвращаемого запросом результата
            CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
            //указываем, в какой таблице ищем
            Root<Employee> employeeRoot = query.from(Employee.class);
            // указываем действия внутри метода select(),
            // которые нужно выполнить, чтобы получить желаемый результат запроса
            query.select(employeeRoot);

            // TypedQuery<X> X - query result type: нужен, чтобы подготовить
            // и  выполнить запрос с критериями, указанными в CriteriaQuery
            TypedQuery<Employee> typedQuery = manager.createQuery(query);
            List<Employee> list = typedQuery.getResultList();

            for (Employee e : list){
                System.out.println(e);
            }




            // сортируем и берём имена
            System.out.println("ORDERED RESULTS:");

            CriteriaQuery<String> query1 = builder.createQuery(String.class);
            Root<Employee> employeeRoot1 = query1.from(Employee.class);
            query1.select(employeeRoot1.get("ename"));
            query1.orderBy(builder.asc(employeeRoot1.get("ename")));

            TypedQuery<String> typedQuery1 = manager.createQuery(query1);
            List<String> nameList = typedQuery1.getResultList();

            for(String name : nameList){
                System.out.println(name);
            }

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
