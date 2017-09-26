import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// Do not modify given code

class Animal {
	private String species;
	public Animal(String species) {
		this.species = species;
	}
	public String getSpecies() {return species;}
}

public class Problem {
	public static void main(String[] args) {
		Dog baeggu = new Dog("Jindo-gae", "Mung!");
		Dog snoopy = new Dog("Beagle", "Bark!");
		Wolf wolf = new Wolf("grey wolf", "Owooooo-");
		inspect(baeggu);
		listen(baeggu);
		inspect(snoopy);
		listen(snoopy);
		inspect(wolf);
		listen(wolf );
	}

	private static void inspect(Animal animal) {
		if(animal instanceof Dog) System.out.println("Dog");
		else if(animal instanceof Wolf) System.out.println("Wolf");		
		System.out.println(animal.getSpecies());		
	}
	
	private static void listen(Barkable barkable) {
		System.out.println(barkable.bark());
	}	
}
// TODO : Code here
// Make 1 interface and 2 classes

interface Barkable {
	String bark();
}

class Dog extends Animal implements Barkable {	
	private String sound;
	public Dog(String species, String s) {
		super(species);
		sound = s;
	}
	public String bark() {
		return sound;
	}	
}

class Wolf extends Animal implements Barkable {	
	private String sound;
	public Wolf(String species, String s) {
		super(species);
		sound = s;
	}
	public String bark() {
		return sound;
	}	
}
