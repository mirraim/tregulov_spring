package mirraim.tregulov_spring.introduction;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Pet {

    public Dog() {
        System.out.println("Dog bean is created");
    }

    @Override
    public void say() {
        System.out.println("Bow-Wow");
    }

    /**
     * Название может быть любым, главное указать его в конфигурационном файле
     * Возвращаемый тип может быль любой, но значение мы получить не сможем
     * Параметров в методах init и destroy быть не должно
     * init-метод вызывается для каждого созданного бина
     */
    public void init() {
        System.out.println("Class Dog: init method");
    }

    /**
     * destroy-метод вызывается только если scope=singleton
     */
    public void destroy() {
        System.out.println("Class Dog: destroy method");
    }
}
