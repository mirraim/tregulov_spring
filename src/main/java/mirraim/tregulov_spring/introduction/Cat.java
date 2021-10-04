package mirraim.tregulov_spring.introduction;

import org.springframework.stereotype.Component;

@Component("catBean") // Bean Id можно не указыватью Дефолтное имя бина - имя класса с маленькой буквы.
public class Cat implements Pet {
    public Cat() {
        System.out.println("Cat bean is created");
    }

    @Override
    public void say() {
        System.out.println("Meow-Meow");
    }
}
