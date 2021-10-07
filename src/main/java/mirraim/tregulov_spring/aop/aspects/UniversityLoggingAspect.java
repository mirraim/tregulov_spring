package mirraim.tregulov_spring.aop.aspects;

import mirraim.tregulov_spring.aop.Student;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {

    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: " +
                "логгируем получение списка студентов перед методом getStudents");
    }

    @AfterReturning(pointcut = "execution(* getStudents())",
                    returning = "students")                 // название переменной в параметрах метода должно совпадат со значением returning
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) { //Также можно использовать Join Point
        for (Student student : students) {
            String fullName = "Mr. " + student.getFullName();
            student.setFullName(fullName);
            double grade = student.getAvgGrade() + 1;
            student.setAvgGrade(grade);
        }

        System.out.println("afterReturningGetStudentsLoggingAdvice: " +
                "логгируем получение списка студентов посл метода getStudents");
    }
}
