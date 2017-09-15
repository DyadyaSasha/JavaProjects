package rest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {

    DatabaseCreateFactory factory = new DatabaseCreateFactory();

    public List<User> getAllUsers() {

        Session session = DatabaseFactory.factory.openSession();

        Transaction transaction = session.beginTransaction();

        List<User> userList = (List<User>) session.createQuery("from rest.User").list();

        transaction.commit();

        session.close();

        return userList;
    }

    public int saveUser(String name, String profession){

        Session session = DatabaseFactory.factory.openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User(name, profession);

        session.save(user);

        transaction.commit();

        session.close();

        return 1;
    }

    public int updateUser(int id, String name, String profession){

        Session session = DatabaseFactory.factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        int flag = 0;
        if (user != null) {
            user.setName(name);
            user.setProfession(profession);
            session.update(user);
            flag = 1;
        }
        transaction.commit();
        session.close();
        return flag;
    }

    public int deleteUser(int id){

        User user;
        Session session = DatabaseFactory.factory.openSession();
        Transaction transaction = session.beginTransaction();

        user = session.get(User.class, id);

        int flag = 0;

        if (user != null){
            session.delete(user);
            flag = 1;
        }

        transaction.commit();
        session.close();

        return flag;

    }

    public User getUser(int id){
        User user;
        Session session = DatabaseFactory.factory.openSession();
        Transaction transaction = session.beginTransaction();
        user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }
}
