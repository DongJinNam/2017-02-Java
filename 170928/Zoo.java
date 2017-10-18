// Example of class inheritance
class Animal {
	private final String name;
	private final int age;
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String info() {
		return this.getClass().getSimpleName() + " " + name + " (" + age + ")";
	}
}
class LandAnimal extends Animal {
	public LandAnimal(String name, int age) {
		super(name, age);
	}
	public String bark() {return "";}
}
class Dog extends LandAnimal {
	public Dog(String name, int age) {
		super(name, age);
	}
	public String bark() {return "bow!";}
}
class Bear extends LandAnimal {
	public Bear(String name, int age) {
		super(name, age);
	}
	public String bark() {return "roar!";}
}
class Bird extends Animal {
	public Bird(String name, int age) {
		super(name, age);
	}
	public boolean canFly() {return true;}
}
class Duck extends Bird {
	public Duck(String name, int age) {
		super(name, age);
	}
}
class Penguin extends Bird {
	public Penguin(String name, int age) {
		super(name, age);
	}
	public boolean canFly() {return false;}
}
//End of Example
//TODO (1) : Make Container class working
//Hint : Use upper bound
class Cage<T extends Animal> {
	private T animal;
	public String info() {
		return "Cage : " + animal.info();
	}
	public void putAnimal(T animal) {
		this.animal = animal;
	}
	public T getAnimal() {
		return animal;
	}
}
// TODO (1) : Make Container class working
// Hint : Use upper bound
class Container<T extends Cage<? extends Animal>> {
	private T cage;
	public String info() {
		return "Container : " + cage.info();
	}
	public void putCage(T cage) {
		this.cage = cage;
	}
	public T getCage() {
		return cage;
	}
}
public class Zoo {
	public static void main(String args[]) {
		Dog dog = new Dog("Pluto", 10);
		Bear bear = new Bear("Bongo", 15);
		Duck duck = new Duck("Donald", 4);
		Penguin penguin = new Penguin("Pablo", 6);
		// TODO (2) : make block 'Output' working
		// Hint : modify type of landContainer variable
		Container<Cage<? extends LandAnimal>> landContainer = new Container<>();
		// Block 'output'
		// Do not modify this block
		Cage<Bear> bearCage = new Cage<>();
		Cage<Dog> dogCage = new Cage<>();
		bearCage.putAnimal(bear);
		landContainer.putCage(bearCage);
		System.out.println(landContainer.info());
		System.out.println(landContainer.getCage().getAnimal().bark());
		dogCage.putAnimal(dog);
		landContainer.putCage(dogCage);
		System.out.println(landContainer.info());
		System.out.println(landContainer.getCage().getAnimal().bark());
		// Block 'output' end
		// TODO (3) : make Block 'Penguin' cannot be compiled
		// Hint : modify type of birdContainer using lower bound
		Container<Cage<? super Bird>> birdContainer = new Container<>();
		Cage<Penguin> penguinCage = new Cage<Penguin>();
		Cage<Bird> birdCage = new Cage<Bird>();
		// TODO (3) : Block 'Duck'
		birdCage.putAnimal(duck);
		birdContainer.putCage(birdCage);
		// Block end
		System.out.println(birdContainer.getCage().getAnimal().info());
		// TODO (3) : Block 'Penguin' : Make this block cannot be compiled
		// Make this block to comment to submit on DomJudge
		//penguinCage.putAnimal(penguin);
		//birdContainer.putCage(penguinCage);
		// Block end
		System.out.println(birdContainer.getCage().getAnimal().info());
	}
}