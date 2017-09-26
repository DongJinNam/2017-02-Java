
interface Inf1 {
	void mt1();
}

interface Inf2 {
	void mt2();
}

interface Inf3 extends Inf1,Inf2 {
	void mt3();
}

class A {
	public void mc1() {
		System.out.println("mc1");
	}
}

class B extends A implements Inf3 {
	public void mt1() {System.out.println("mt1");}
	public void mt2() {System.out.println("mt2");}
	public void mt3() {System.out.println("mt3");}
}

public class InfTest2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Inf1 i1 = new B();
		Inf2 i2 = (Inf2) i1;
		Inf3 i3 = (Inf3) i2;
		i2 = (Inf3) i1;
		A a = (A) i1;
		B b = (B) i1;
		a = new A();
		
		i2.mt2();		
		
		b.mt1();
		b.mt2();
		b.mt3();
		
		try {
			b = (B) a; // 자식 = 부모는 클래스 캐스팅이 안되기 때문에, 예외처리가 필요하다.
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		// true,false 맞추는 문제
		System.out.println(i1 instanceof B); // true i1의 실객체는 B 이기 때문에
		System.out.println(a instanceof B); // false a의 실객체는 A 이기 때문에
		
	}
}
