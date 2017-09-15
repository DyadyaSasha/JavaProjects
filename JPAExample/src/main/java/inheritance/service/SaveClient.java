package inheritance.service;

import inheritance.tableperclass.NonTeachingStaff;
import inheritance.tableperclass.TeachingStaff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SaveClient {

    public static void main(String[] args){

        EntityManagerFactory factory = null;
        EntityManager manager =null;

        try {
            factory = Persistence.createEntityManagerFactory("Eclipse_JPA");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();

            /**
             * manager.persist() сохранит все экземпляры классов в иерархии насследования
             * в одну таблицу(strategy = InheritanceType.SINGLE_TABLE) под именем суперкласса
             * при этом создаётся таблица SEQUENCE(которая похоже вспомогательная,
             * так как после создания таблиц она становиться пустой)
             */
//            TeachingStaff ts1 = new TeachingStaff(1,"Gopal","MSc MEd","Maths");
//            TeachingStaff ts2 = new TeachingStaff(2, "Manisha", "BSc BEd", "English");
//
//            NonTeachingStaff nts1 = new NonTeachingStaff(3, "Satish", "Accounts");
//            NonTeachingStaff nts2 = new NonTeachingStaff(4, "Krishna", "Office Admin");
            /**
             * manager.persist() сохранит все экземпляры классов в три таблицы(на каждый класс одна таблица)
             * таблицы субклассов будут содержать только те поля, которые объявлены в классе + внешний ключ(FK),
             * таблица суперкласса будет содержать свои поля, одним из которых будет первичный ключ(PK),
             * который мапиться с внешним ключом(FK) таблиц субклассов, + колонку(поле), в которой указано
             * имя субкласса, у которого внешний ключ экземпляра(строки в таблице) соответсвует первичному
             * ключу в таблице суперкласса
             * */
//            TeachingStaff ts1 = new TeachingStaff(1,"Gopal","MSc MEd","Maths");
//            TeachingStaff ts2 = new TeachingStaff(2, "Manisha", "BSc BEd", "English");
//
//            NonTeachingStaff nts1 = new NonTeachingStaff(3, "Satish", "Accounts");
//            NonTeachingStaff nts2 = new NonTeachingStaff(4, "Krishna", "Office Admin");

            /**
             * manager.persist() сохранит все экземпляры классов в три таблицы(для каждого класса своя таблица )
             * */
            TeachingStaff ts1 = new TeachingStaff(1,"Gopal","MSc MEd","Maths");
            TeachingStaff ts2 = new TeachingStaff(2, "Manisha", "BSc BEd", "English");

            NonTeachingStaff nts1 = new NonTeachingStaff(3, "Satish", "Accounts");
            NonTeachingStaff nts2 = new NonTeachingStaff(4, "Krishna", "Office Admin");


            manager.persist(ts1);
            manager.persist(ts2);
            manager.persist(nts1);
            manager.persist(nts2);

            manager.getTransaction().commit();

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
