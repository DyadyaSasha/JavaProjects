import entities.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CriteriaAPI {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();
        /**
         * deprecated - рекомендуется использовать JPA Criteria API
         * */
        Criteria criteria = session.createCriteria(Employee.class);
        List list = criteria.list();
        for (Employee e: (List<Employee>)list){
            System.out.println(e.getFirstName());
        }


        factory.close();

    }

}
