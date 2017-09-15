package relation.service;

import relation.data.manytoone.DepEmployee;
import relation.data.manytoone.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToOne {

    public static void main(String[] args){

/**
 * В таблице DepEmployee будет дополнительная колонка внешних ключей(FK) - departmeny_id */
        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();

            Department department = new Department();
            department.setName("Development");

            manager.persist(department);

            DepEmployee employee1 = new DepEmployee();
            employee1.setEname("Satish");
            employee1.setSalary(45000.0);
            employee1.setDeg("Technical Writer");
            employee1.setDepartment(department);

            DepEmployee employee2 = new DepEmployee();
            employee2.setEname("Krishna");
            employee2.setSalary(45000.0);
            employee2.setDeg("Technical Writer");
            employee2.setDepartment(department);

            DepEmployee employee3 = new DepEmployee();
            employee3.setEname("Masthanvali");
            employee3.setSalary(50000.0);
            employee3.setDeg("Technical Writer");
            employee3.setDepartment(department);


            manager.persist(employee1);
            manager.persist(employee2);
            manager.persist(employee3);

            manager.getTransaction().commit();

            manager.close();
            factory.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager.isOpen()) manager.close();
            if (manager.isOpen()) factory.close();
        }

    }

}
