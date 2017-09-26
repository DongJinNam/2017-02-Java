// Generic Class를 이용해서 이상한 타입을 컴파일러가 알아서 잡아낸다.
// T : Type Parameter(타입 파라미터 보통 대문자 T로 사용)
// E : Element
// K : Key
// V : Value
// N : Number
public class Box<T>  {
	private T data; // int, double 등등 다 받을 수 있음.
	public Box(T d) {
		data = d;
	}
	public void setData(T d) {
		data = d;
	}
	public T getData() {
		return data;
	}
	
	public static void main(String args[]) {
		Box<Integer> b = new Box<Integer>(100);
		b.setData(200); // java 8에서 int -> Integer로 자동으로 바꿔줌.
		System.out.println(b.getData());
	}
}


