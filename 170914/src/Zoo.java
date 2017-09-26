class Animal {
	private final String name;
	private final int age;
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() {
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
 
class Bear extends LandAnimal{
	// TODO : Make this class (1)
	public Bear(String name, int age) {
		super(name, age);
	}		
	// Barking sound : roar!
	public String bark() {return "roar!";}	
}

class Bird extends Animal{
	// TODO : Make this class and children (1)
	// Parent : Animal
	public Bird(String name, int age) {
		super(name,age);
	}	
	// Child : Duck, Penguin
	public boolean canFly() {
		return true;		
	}
	// Duck can fly(return true), Penguin cannot fly(return false);
}

class Duck extends Bird{
	// Parent : Bird
	public Duck(String name, int age) {
		super(name,age);
	}		
}

class Penguin extends Bird{
	// Parent : Bird
	public Penguin(String name, int age) {
		super(name,age);
	}		
	// function : boolean canFly() is needed
	public boolean canFly() {
		return false;
	}
}

public class Zoo {
	public static void main(String args[]) {
		Animal[] animalArray = new Animal[5];
		// TODO : Make this code working
		// make classes Bear, Duck, Penguin
		animalArray[0] = new Animal("Unknown", 0);
		animalArray[1] = new Dog("Pluto", 10);
		animalArray[2] = new Bear("Bongo", 15);
		animalArray[3] = new Duck("Donald", 4);
		animalArray[4] = new Penguin("Pablo", 6);
		for(int i = 0; i < animalArray.length; i++) {
			Animal animal = animalArray[i];
			System.out.println(animal);
			if (animal instanceof LandAnimal) {
				LandAnimal landAnimal = (LandAnimal) animal;
				System.out.println(landAnimal.bark());				
			}
			if (animal instanceof Bird) {
				Bird bird = (Bird) animal;
				System.out.println(bird.canFly());				
			}
		}
	}
}