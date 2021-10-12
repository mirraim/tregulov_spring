package mirraim.tregulov_spring.hiber_source;

import mirraim.tregulov_spring.hiber_source.entity.Detail;
import mirraim.tregulov_spring.hiber_source.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RelationsTest {

    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Employee employee = new Employee(
                    "Tom", "Newman", "HR", 500
            );
            Detail detail = new Detail(
                    "Sochi", "2222222", "222@mail.ru"
            );
            employee.setEmpDetail(detail);
            session.save(employee);
            Employee emp = session.get(Employee.class, 1);
            session.getTransaction().commit();
            System.out.println(emp.getEmpDetail());
        }
    }
}
