package mirraim.tregulov_spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
          MyConfig.class
        );
        Library library = context.getBean("libBean", Library.class);
        library.getBook();
        library.returnBook();
        library.getMagazine();
        library.addBook();
        library.returnMagazine();
        context.close();
    }
}
