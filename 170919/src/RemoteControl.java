//interface 정의
//인터페이스를 사용해야 하는 이유
//핵심 : 인터페이스 자체가 하나의 부모 타입이 된다.
//이 인터페이스를 구현한 클래스에는 무조건 turnOn,turnOff가 있다.
//일종의 규약임
public interface RemoteControl {
	void turnOn(); // 인터페이스 내에 메소드 들은 자동으로 public abstract를 자바가 몰래 써준다.
	void turnOff();
}


