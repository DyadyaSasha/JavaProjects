package relation.service;

import relation.data.manytomany.Clas;
import relation.data.manytomany.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;
/**
 * создастся 4 таблицы: две на каждый из классов
 * и ещё по две маппинговые - TEACHER_CLAS и CLAS_TEACHER
 * */
public class ManyToMany {
    public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();

            Clas clas1 = new Clas(0, "1st", null);
            Clas clas2 = new Clas(0, "2nd", null);
            Clas clas3 = new Clas(0, "3rd", null);


            manager.persist(clas1);
            manager.persist(clas2);
            manager.persist(clas3);

            Set<Clas> classSet1 = new HashSet();
            classSet1.add(clas1);
            classSet1.add(clas2);
            classSet1.add(clas3);

            Set<Clas> classSet2 = new HashSet();
            classSet2.add(clas3);
            classSet2.add(clas1);
            classSet2.add(clas2);

            Set<Clas> classSet3 = new HashSet();
            classSet3.add(clas2);
            classSet3.add(clas3);
            classSet3.add(clas1);

            Teacher teacher1 = new Teacher(0, "Satish","Java",classSet1);
            Teacher teacher2 = new Teacher(0, "Krishna","Adv Java",classSet2);
            Teacher teacher3 = new Teacher(0, "Masthanvali","DB2",classSet3);

            manager.persist(teacher1);
            manager.persist(teacher2);
            manager.persist(teacher3);

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
