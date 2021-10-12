package mirraim.tregulov_spring.hiber_source;

import mirraim.tregulov_spring.hiber_source.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
//            Employee employee = new Employee(
//                    "Helen", "Parker", "Sales", 650
//            );
//           store.save(employee);
//           int id = employee.getId();
//           System.out.println(store.getById(id));
            List<Employee> employees = store.getByName("Mike");
            employees.forEach(System.out::println);
        } finally {
            store.close(); // SessionFactory всегда должна быть закрыта
        }
    }

    /**
     * Метод сохраняет в БД объект Employee
     * Соединение с БД обеспечивает объект Session.
     * Session заново создается для каждого подключения к БД
     * Между началом транзакции и коммитом можно произвести несколько операций
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
     * Метод получает из БД объект Employee
     * Поиск производится по id, который принимается как параметр
     * Соединение с БД обеспечивает объект Session.
     * @return employee или null в случае отсутствия id в базе
     */
    public Employee getById(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    /**
     * Метод получает из БД список всех объектов Employee
     * Соединение с БД обеспечивает объект Session.
     * @return список employee или пустой List
     */
    public List<Employee> getList() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        // здесь пишется имя класса, а не имя таблицы
        List<Employee> employees = session.createQuery("from Employee")
                        .getResultList();
        session.getTransaction().commit();
        return employees;
    }

    /**
     * Метод получает из БД объект Employee
     * Поиск производится по id, который принимается как параметр
     * Соединение с БД обеспечивает объект Session.
     * @return employee или null в случае отсутствия id в базе
     */
    public List<Employee> getByName(String empName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        // .createQuery может содержать любой запрос на HQL
        List<Employee> employees = session.createQuery(
                "from Employee where name='" + empName + "'"
                ).getResultList();
        session.getTransaction().commit();
        return employees;
    }

    /**
     * Метод закрывает объект SessionFactory
     */
    public void close() {
        factory.close();
    }
}
