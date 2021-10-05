package mirraim.tregulov_spring.introduction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class Cat implements Pet {
    public Cat() {
        System.out.println("Cat bean is created");
    }

    @Override
    public void say() {
        System.out.println("Meow-Meow");
    }
}
