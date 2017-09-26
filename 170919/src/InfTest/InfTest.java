
interface Inf1 {
	void mt1();
}

interface Inf2 {
	void mt2();
}

interface Inf3 extends Inf1,Inf2 {
	void mt3();
}

interface Inf4 {
	void mt4();
}

class A implements Inf3,Inf4 {
	public void mt1() {System.out.println("mt1");}
	public void mt2() {System.out.println("mt2");}
	public void mt3() {System.out.println("mt3");}
	public void mt4() {System.out.println("mt4");}
}


public class InfTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		
		Inf1 i1 = a;
		Inf3 i3 = a;
		Inf2 i2 = i3;
		Inf4 i4 = a;
		i3.mt1();
		i3.mt2();
		i3.mt3();
		//i3.mt4();
	}
}
