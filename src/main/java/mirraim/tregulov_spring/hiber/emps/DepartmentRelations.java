package mirraim.tregulov_spring.hiber.emps;

import mirraim.tregulov_spring.hiber.emps.entity.Department;
import mirraim.tregulov_spring.hiber.emps.entity.Detail;
import mirraim.tregulov_spring.hiber.emps.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DepartmentRelations {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory()) {
            Department dep = new Department(
                    "IT", 300, 1200
            );
            Employee emp1 = new Employee(
                    "Sam", "Daniels", 700
            );
            Employee emp2 = new Employee(
                    "Tom", "Jones", 1100
            );
            add(factory, dep, List.of(emp1, emp2));
            Department department = getDepartment(factory, 1);
            System.out.println(department);
            System.out.println(department.getEmployees());
            Employee employee = getEmployee(factory, 2);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
            deleteEmployee(factory, 2);
        }
    }

    public static void add(SessionFactory factory,
                           Department dep, List<Employee> employees) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            employees.forEach(dep::addEmployee);
            session.save(dep);
            session.getTransaction().commit();
        }
    }

    public static Department getDepartment(SessionFactory factory, int id) {
        Department department = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            department = session.get(Department.class, id);
           // System.out.println(department.getEmployees());
            session.getTransaction().commit();
        }
        return department;
    }

    public static Employee getEmployee(SessionFactory factory, int id) {
        Employee employee = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            employee = session.get(Employee.class, id);
            session.getTransaction().commit();
        }
        return employee;
    }

    public static void deleteEmployee(SessionFactory factory, int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            session.getTransaction().commit();
        }
    }
}
