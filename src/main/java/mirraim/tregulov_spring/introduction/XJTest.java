package mirraim.tregulov_spring.introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XJTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
          "appJContext.xml"
        );
        Cat cat = context.getBean("catBean", Cat.class);
        cat.say();
        Dog dog = context.getBean("dog", Dog.class);
        dog.say();
        context.close();
    }
}
