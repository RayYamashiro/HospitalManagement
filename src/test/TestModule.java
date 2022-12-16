

import org.example.Doctor;
import org.example.HibernateUtil;
import org.example.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;




import static org.junit.Assert.assertEquals;

public class TestModule {


    private static SessionFactory sessionFactory;
    private Session session;


    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }


    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test public void testCreate() {
        System.out.println("Running testCreate...");

        session.beginTransaction();

        Person person = new Person("Алекс" , "Иванов", "ivanov11123@mail.ru", "12345" , "79512344848", Person.Sex.Мужчина);
        Integer id = (Integer) session.save(person);

        session.getTransaction().commit();

        Assertions.assertTrue(id > 0);
    }

    @Test public void testUpdate() {
        System.out.println("Running testUpdate...");

        Integer id = 8;
        Person person = new Person("Алекс" , "Иванов", "ivanov11123@mail.ru", "12345" , "79512344848", Person.Sex.Мужчина);

        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();

        Person updatedProduct = session.find(Person.class, id);

        assertEquals("Алекс", updatedProduct.getName());
    }

    @Test
    public void testGet() {
        System.out.println("Running testGet...");

        Integer id = 8;

        Person product = session.find(Person.class, id);

        assertEquals("Алекс", product.getName());
    }

    @Test
    public void testList() {
        System.out.println("Running testList...");

        Query<Person> query = session.createQuery("from Person ", Person.class);
        List<Person> resultList = query.getResultList();

        Assertions.assertFalse(resultList.isEmpty());
    }
    @Test
    public void testGet1() {
        System.out.println("Running testGet...");

        Integer id = 7;

        Person product = session.find(Person.class, id);

        assertEquals("Алекс", product.getName());
    }
    @Test
    public void testDelete() {
        System.out.println("Running testDelete...");

        Integer id = 8;
        Person product = session.find(Person.class, id);

        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();

        Person deletedProduct = session.find(Person.class, id);

        Assertions.assertNull(deletedProduct);
    }
}
