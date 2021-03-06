package mirraim.tregulov_spring.introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JConfTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
               JavaConfig.class
        );
        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());

        context.close();
    }
}
