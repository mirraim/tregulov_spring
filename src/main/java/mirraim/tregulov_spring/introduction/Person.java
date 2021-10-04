package mirraim.tregulov_spring.introduction;

public class Person {
    private Pet pet;
    private String surname;
    private int age;

    public Person(Pet pet) {
        this.pet = pet;
    }

    public Person() {
    }

    /**
     * автоматически формируемый бин будет иметь
     * name = название метода с маленькой буквы, отбросив set, т.е. "pet"
     * @param pet pet
     */
    public void setPet(Pet pet) {
        System.out.println("Class Person: set pet");
        this.pet = pet;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void callYourPet() {
        System.out.println("Hello, my pet!");
        pet.say();
    }
}
