package mirraim.tregulov_spring.aop;

import org.springframework.stereotype.Component;

@Component("libBean")
public class Library {

    public void getBook() {
        System.out.println("Берем книгу ");
    }

    public void returnBook() {
        System.out.println("Возвращаем книгу");
    }

    public void getMagazin() {
        System.out.println("Берем журнал номер");
    }
}
