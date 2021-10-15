package mirraim.tregulov_spring.hiber.emps;

import mirraim.tregulov_spring.hiber.emps.entity.Department;
import mirraim.tregulov_spring.hiber.emps.entity.Detail;
import mirraim.tregulov_spring.hiber.emps.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DetailsRelations {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory()) {
            Employee employee = new Employee(
                    "Sam", "Daniels", 1500
            );
            Detail detail = new Detail(
                    "Vienna", "7777777", "777@mail.ru"
            );
            saveEmployee(factory, employee, detail);
            int id = detail.getId();
            Employee emp = getEmployeeByDetail(factory, id);
            System.out.println(emp);
            System.out.println(emp.getEmpDetail());
            deleteDetail(factory, id);
            System.out.println(emp.getEmpDetail());
        }
    }


    public static void saveEmployee(SessionFactory factory, Employee employee, Detail detail) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            employee.setEmpDetail(detail);
            session.save(employee);
            session.getTransaction().commit();
        }
    }

    public static void saveDetail(SessionFactory factory, Employee employee, Detail detail) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            employee.setEmpDetail(detail);
            detail.setEmployee(employee);
            session.save(detail);

            session.getTransaction().commit();
        }
    }

    public static Employee getEmployeeByDetail(SessionFactory factory, int id) {
        Employee emp;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Detail detail = session.get(Detail.class, id);
            emp = detail.getEmployee();
            session.getTransaction().commit();
            System.out.println(emp);
        }
        return emp;
    }

    public static void deleteEmployee(SessionFactory factory, int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
        }
    }

    public static void deleteDetail(SessionFactory factory, int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Detail detail = session.get(Detail.class, id);
            detail.getEmployee().setEmpDetail(null);
            session.delete(detail);
            session.getTransaction().commit();
        }
    }
}
