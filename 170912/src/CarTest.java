
class Car extends Object {
	// field area
	int color;
	int speed;
	int mileage;
	static int count = 0;
	// method area
	// constructor
	public Car(int c,int s,int m) {
		System.out.println("3인자 생성자 호출됨");
		color = c;
		speed = s;
		mileage = m;
	}
	
	public Car() {
		System.out.println("Car default 생성자 호출됨");
		color = 0;
		speed = 0;
		mileage = 0;
	}
	
	// setter
	void setColor(int c) {
		color = c;
	}
	void setSpeed(int s) {
		speed = s;	
	}
	void setMileage(int m) {
		mileage = m;
	}
	// getter
	int getColor() {
		return color;
	}
	int getSpeed() {
		return speed;
	}
	int getMileage() {
		return mileage;
	}
	
	// business logic을 담은 함수들	
	void speedUp(int s) {
		speed += s;
	}
	void speedDown(int s) {
		speed -= s;
	}	
	
	public String toString() { // Object Class의 Default toString() Override
		return "speed : " + speed + ", mileage : " + mileage + ", color : " + color;
	}
}

class SportsCar extends Car {
	int highSpeed;
	public SportsCar(int h) {
		super(1,1,1); // 위 클래스 3인자 생성자 명시적 호출
		System.out.println("SportsCar default 생성자 호출됨");
		highSpeed = h;
	}	
	void speedUp(int s) { // overriding
		System.out.println("SportsCar speedup 호출됨");
		speed = s*10;
		highSpeed = speed;
	}
}

public class CarTest {
	public static void main(String args[]) {
//		Car c1 = new Car();
//		Car c2 = new Car(1,220,70000);
//		
//		c1.speedUp(10);
//		c2.speedUp(20);
//		
//		System.out.println(c1.getSpeed());
//		System.out.println(c2.getSpeed());
//		
//		System.out.println(c1);
//		System.out.println(c2);
		
		// 3인칭 생성자 호출 경우. Car 3인자 생성자 호출 후, SportsCar 생성자가 호출됨
		SportsCar s1 = new SportsCar(10);		
		s1.speedUp(50);
	}
}