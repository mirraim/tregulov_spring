package mirraim.tregulov_spring.aop;

import org.springframework.stereotype.Component;

@Component("libBean")
public class Library {

    public void getBook() {
        System.out.println("Берем книгу");
        System.out.println("--------------------------------------");
    }

    public void returnBook() {
        System.out.println("Возвращаем книгу");
        System.out.println("--------------------------------------");
    }

    public void getMagazine() {
        System.out.println("Берем журнал");
        System.out.println("--------------------------------------");
    }

    public void returnMagazine() {
        System.out.println("Возвращаем журнал");
        System.out.println("--------------------------------------");
    }

    public void addBook(String personName, Book book) {
        System.out.println("Добавляем новую книгу");
        System.out.println("--------------------------------------");
    }

    public void addMagazine() {
        System.out.println("Добавляем новый журнал");
        System.out.println("--------------------------------------");
    }
}
