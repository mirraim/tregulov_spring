package mirraim.tregulov_spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
          MyConfig.class
        );
        Library library = context.getBean("libBean", Library.class);
        Book book = context.getBean("book", Book.class);
        library.getBook(book);
        library.getMagazin(12);
        context.close();
    }
}
