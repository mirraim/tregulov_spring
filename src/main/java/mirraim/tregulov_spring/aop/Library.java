package mirraim.tregulov_spring.aop;

import org.springframework.stereotype.Component;

@Component("libBean")
public class Library {

    public void getBook() {
        System.out.println("Берем книгу");
    }

    public void returnBook() {
        System.out.println("Возвращаем книгу");
    }

    public void getMagazine() {
        System.out.println("Берем журнал");
    }

    public void returnMagazine() {
        System.out.println("Возвращаем журнал");
    }

    public void addBook() {
        System.out.println("Добавляем новую книгу");
    }

    public void addMagazine() {
        System.out.println("Добавляем новый журнал");
    }
}
