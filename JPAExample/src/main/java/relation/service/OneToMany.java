package relation.service;

import relation.data.onetomany.DepEmployee;
import relation.data.onetomany.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class OneToMany {
/**
 * будет три таблицы: по одной на каждый класс и третья - маппинг employee с department
 * */
    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();

            DepEmployee employee1 = new DepEmployee();
            employee1.setEname("Satish");
            employee1.setSalary(45000.0);
            employee1.setDeg("Technical Writer");


            DepEmployee employee2 = new DepEmployee();
            employee2.setEname("Krishna");
            employee2.setSalary(45000.0);
            employee2.setDeg("Technical Writer");


            DepEmployee employee3 = new DepEmployee();
            employee3.setEname("Masthanvali");
            employee3.setSalary(50000.0);
            employee3.setDeg("Technical Writer");


            manager.persist(employee1);
            manager.persist(employee2);
            manager.persist(employee3);


            List<DepEmployee> emplist = new ArrayList();
            emplist.add(employee1);
            emplist.add(employee2);
            emplist.add(employee3);


            Department department = new Department();
            department.setName("Development");
            department.setEmployeeList(emplist);


            manager.persist(department);

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
