package mirraim.tregulov_spring.aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class University {

    private List<Student> students = new ArrayList<>();

    public void addStudents() {
        students.add(new Student("Ivan Petrov", 3, 5.4));
        students.add(new Student("Petr Sidorov", 2, 6.3));
        students.add(new Student("Anna Ivanova", 1, 4.8));
    }

    public List<Student> getStudents() {
        System.out.println("Начало работы метода getStudents");
        //students.get(3);
        System.out.println("List of students: ");
        System.out.println(students);
        return students;
    }
}
