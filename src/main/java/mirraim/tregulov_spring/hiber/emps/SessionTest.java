package mirraim.tregulov_spring.hiber.emps;

import mirraim.tregulov_spring.hiber.emps.entity.Employee;
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
//            List<Employee> employees = store.getByName("Mike");
//            employees.forEach(System.out::println);
//            store.setSalaries("Mike", 750);
//            store.delete(1);
            store.deleteByName("Mike");
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
                "from Employee where name='" + empName + "'", Employee.class
                ).getResultList();
        session.getTransaction().commit();
        return employees;
    }

    public void setSalary(int id, int salary) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        employee.setSalary(salary);
        session.getTransaction().commit();
    }

    public void setSalaries(String empName, int salary) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        // здесь пишется имя класса, а не имя таблицы
        session.createQuery(
                "update Employee set salary = " + salary +" where name='" + empName + "'"
        ).executeUpdate();
        session.getTransaction().commit();
    }

    public void delete(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
    }

    public void deleteByName(String empName) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery(
                "delete Employee where name='" + empName + "'"
        ).executeUpdate();
        session.getTransaction().commit();
    }

    /**
     * Метод закрывает объект SessionFactory
     */
    public void close() {
        factory.close();
    }
}
