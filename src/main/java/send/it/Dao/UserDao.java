//package send.it.Dao;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import send.it.Database.Database;
//import send.it.DatabaseTables.UsersTable;
//import send.it.HibernateUtil;
//
//public class UserDao {
//
//    static final Logger logger = LoggerFactory.getLogger(UserDao.class);
//
//
//    public UserDao() {
//        Database.getConnection();
//    }
//
//
//    public void saveUser(UsersTable user) {
//        Transaction transaction;
//        try (Session session = HibernateUtil.openSession()){
//            transaction = session.beginTransaction();
//
//            session.persist(user);
//            transaction.commit();
//            logger.info("User stored in database.");
//        } catch (HibernateException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public UsersTable getUser(String username) {
//        UsersTable loginUser = null;
//        try (Session session = HibernateUtil.openSession()) {
//            String hql = "FROM UsersTable U WHERE U.username = :username";
//            @SuppressWarnings({ "deprecation", "rawtypes" })
//            Query query = session.createQuery(hql);
//            query.setParameter("username", username);
//            loginUser = (UsersTable) query.getSingleResult();
//            logger.info("Username found.");
//        }catch (Exception e) {
//            logger.error("Error: {}", String.valueOf(e));
//        }
//        return loginUser;
//    }
//}