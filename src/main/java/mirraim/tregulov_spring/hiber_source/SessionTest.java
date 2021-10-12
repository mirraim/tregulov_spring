package mirraim.tregulov_spring.hiber_source;

import mirraim.tregulov_spring.hiber_source.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionTest {
    private SessionFactory factory;

    private SessionTest(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Объект SessionFactory - тяжелый, его создание забирает много ресурсов
     * Его целесообразно создавать один раз за все время работы программы
     * Объект SessionFactory после создания передается в приватный конструктор класса SessionTest
     * @return SessionTest
     */
    public static SessionTest init() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        return new SessionTest(factory);
    }

    public static void main(String[] args) {
       SessionTest store = SessionTest.init();
        try {
            Employee employee = new Employee(
                    "Mike", "Hornby", "IT", 800
            );
           store.save(employee);
        } finally {
            store.close(); // SessionFactory всегда должна быть закрыта
        }
    }

    /**
     * Метод сохраняет в БД объект Employee
     * Соединение с БД обеспечивает объект Session. Этот объект заново создается
     * для каждого подключения к БД
     * @return employee с измененным id
     */
    public Employee save(Employee employee) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        return employee;
    }

    /**
     * Метод закрывает объект SessionFactory
     */
    public void close() {
        factory.close();
    }
}
