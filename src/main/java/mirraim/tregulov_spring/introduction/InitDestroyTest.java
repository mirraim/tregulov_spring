package mirraim.tregulov_spring.introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitDestroyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "appContext.xml"

        );
        Dog dog = context.getBean("myPet", Dog.class);
        dog.say();
        context.close();
    }
}
