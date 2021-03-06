package mirraim.tregulov_spring.aop.aspects;

import mirraim.tregulov_spring.aop.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {

    /**
     * Выполняется до целевого метода
     */
    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: " +
                "логгируем получение списка студентов перед методом getStudents");
    }

    /**
     * Выполняестя после успешно завершившего работу метода
     * Название переменной в параметрах метода должно совпадат со значением returning
     * @param students Также можно использовать Join Point
     */
    @AfterReturning(pointcut = "execution(* getStudents())",
                    returning = "students")
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) {
        for (Student student : students) {
            String fullName = "Mr. " + student.getFullName();
            student.setFullName(fullName);
            double grade = student.getAvgGrade() + 1;
            student.setAvgGrade(grade);
        }

        System.out.println("afterReturningGetStudentsLoggingAdvice: " +
                "логгируем получение списка студентов после метода getStudents");
    }

    /**
     * Исполняется после выброса исключения в целевом методе.
     * название переменной в параметрах метода должно совпадат со значением throwing
     * @param ex Также можно использовать Join Point в качестве параметра
     */
    @AfterThrowing(pointcut = "execution(* getStudents())",
                    throwing = "ex")
    public void afterThrowingGetStudentsLoggingAdvice(Throwable ex) {
        System.out.println("afterThrowingGetStudentsLoggingAdvice: " +
                "логгируем исключение во время работы метода getStudents" + ex);
    }

    /**
     * Выполняется после окончания метода с основной логикой
     * вне зависимости от того, завершается ли метод нормально
     * или было выброшено исключение
     * Также можно использовать Join Point в качестве параметра
     * К исключению или возвращаемому значению доступ получить нельзя
     */
    @After("execution(* getStudents())")
    public void afterGetStudentsLoggingAdvice() {
        System.out.println("afterGetStudentsLoggingAdvice: " +
                "логгируем завершение работы метода getStudents");
    }
}
