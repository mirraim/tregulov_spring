package mirraim.tregulov_spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ReturnBookTest {
    public static void main(String[] args) {
        System.out.println("Method main starts");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MyConfig.class
        );
        Library library = context.getBean("libBean", Library.class);
        String bookName = library.returnBook();
        System.out.println("В библиотеку вернули книгу " + bookName);
        context.close();
        System.out.println("Method main ends");
    }
}
