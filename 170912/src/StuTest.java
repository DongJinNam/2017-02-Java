
class Person {
	String name;
	int age;
	public Person() {
		
	}
	public Person(String n, int a) {
		name = n;
		age = a;
	}
	public void setAge(int a) {
		age = a;
	}
	public int getAge() {
		return age;
	}
}

class Student extends Person {
	int ID;
	int score;
	static int COUNT = 0;
	
	public Student() {
		super("noname",0);
		score = 0;
	}
	public Student(String n,int i,int a,int s) {
		super(n,a);
		ID = i;
		score = s;
		setCOUNT();
	}
	public void setScore(int s) {
		score = s;
	}
	public void setAge(int a) {
		age = a;
	}	
	public void setName(String n) {
		name = n;
	}
	public void setID(int i) {
		ID = i;
	}
	public void setCOUNT() {
		COUNT++;
	}
	public int getScore() {
		return score;
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}	
	public String toString() {
		return "ID : " + ID + ", name : " + name + ", age : " + age;
	}
}

public class StuTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student arr[] = new Student[3];
		arr[0] = new Student("kim",20140201,20,99);
		arr[1] = new Student("park",20140202,21,93);
		arr[2] = new Student("lee",20140203,23,85);				
		System.out.println("Count : " + Student.COUNT);
		for (int i = 0; i < Student.COUNT; i++) {
			System.out.println(arr[i]);
		}
	}
}
