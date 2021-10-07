package mirraim.tregulov_spring.aop;

import org.springframework.stereotype.Component;

@Component("libBean")
public class Library {

    public void getBook(Book book) {
        System.out.println("Берем книгу " + book.getName());
    }

    public void returnBook() {
        System.out.println("Возвращаем книгу");
    }

    public void getMagazin(int number) {
        System.out.println("Берем журнал номер " + number);
    }
}
