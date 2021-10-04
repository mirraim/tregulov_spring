package mirraim.tregulov_spring.introduction;

public class Person {
    private Pet pet;

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

    public void callYourPet() {
        System.out.println("Hello, my pet!");
        pet.say();
    }
}
