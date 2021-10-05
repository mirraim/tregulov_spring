package mirraim.tregulov_spring.introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XJTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
          "appJContext.xml"
        );
        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        context.close();
    }
}
