package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory=Util.getSessionFactory();
    private static final String CREAT_USER_TABLE = "CREATE TABLE IF NOT EXISTS human " +
            "(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name varchar(40) NOT NULL, " +
            "lastname varchar(40) NOT NULL, age TINYINT)";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE human";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS human";


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREAT_USER_TABLE).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_USER_TABLE).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            session.getTransaction().commit();
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session =sessionFactory.openSession()) {
            session.beginTransaction();
            User newUser = session.get(User.class, id);
            if (newUser != null)
                session.delete(newUser);


            session.getTransaction().commit();
        }
    }


    @Override
    public List<User> getAllUsers() {

List<User> list=new ArrayList<>();
        try (Session session =sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String str="from User";
            list=session.createQuery(str).getResultList();
            session.getTransaction().commit();

        }catch (Exception e ){
            e.printStackTrace();
        }
            return list;


    }
    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.createSQLQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();


        }

    }
}
