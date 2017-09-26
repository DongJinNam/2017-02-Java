class Cleaner implements RemoteControl {
	// Remote Control Implements 한 사실만 가지고도 turnOn,turnOff 메소드가 
	// 선언됬다는 것을 명시적으로 알 수 있다.	
	public void turnOn() {
		System.out.println("청소기 on");
	}
	public void turnOff() {
		System.out.println("청소기 off");
	}	
}

public class Television implements RemoteControl {
	// 이렇게만 적어도 public abstract 라고 자바가 넣어둠
	public void turnOn() {
		System.out.println("TV on");
	}
	public void turnOff() {
		System.out.println("TV off");
	}
	public static void main(String args[]) {
		// 밑에처럼 하면 안됨.
		/**
		Television t1 = new Television();
		Cleaner c1 = new Cleaner();
		t1.turnOn();
		t1.turnOff();
		c1.turnOn();
		c1.turnOff();
		*/
		// 인터페이스가 하나의 타입이며 부모 역할을 한다(매우 중요)
		RemoteControl[] arr = new RemoteControl[2];
		arr[0] = new Television();
		arr[1] = new Cleaner();
		for (int i = 0; i < 2; i++) {
			arr[i].turnOn();
			arr[i].turnOff();
		}
		
	}
}
