package mirraim.tregulov_spring.hiber.one_to_many_uni;

import mirraim.tregulov_spring.hiber.one_to_many_uni.entity.Department;
import mirraim.tregulov_spring.hiber.one_to_many_uni.entity.Employee;
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
                .buildSessionFactory()) {
            Department dep = new Department(
                    "HR", 500, 1500
            );
            Employee emp1 = new Employee(
                    "Emma", "Chain", 900
            );
            Employee emp2 = new Employee(
                    "John", "Black", 1300
            );
            add(factory, dep, List.of(emp1, emp2));
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
}
