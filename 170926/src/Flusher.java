

interface Sink<T> {
	public void flush(T t);
}

class ConsoleSink<T> implements Sink<T> {
	public void flush(T t) {
		System.out.println(t);
	}
}

public class Flusher {
	public static <T> T writeAll(Vector<T> data, Sink<T> sink) {
		T last = null;
		sink.flush(data);
		return last;
	}
	
	public static <T> T writeAll2(Vector<? extends T> data, Sink<? extends T> sink) {
		T last = null;
		sink.flush(data);
		return last;
	}
	
	public static <T> T writeAll3(Vector<? super T> data, Sink<? super T> sink) {
		T last = null;
		sink.flush(data);
		return last;
	}		
	
	public static void main(String args[]) {
		Sink<Object> sink= new ConsoleSink<Object>();
		Vector<String> vec = new Vector<String>();
		vec.add("Hello");
		
		//(1 - problem) - Type이 맞지 않기 때문에
		//Flusher.writeAll(vec,sink); compile error
		//(2 - ok) - String class가 Object class를 extends 하였기 때문에
		// T를 부모로 하는 모든 것이 다 됨.
		// T=Object String = ? extends Object
		//Object o = Flusher.writeAll2(vec, sink);
		//(3 - problem)
		//String str = Flusher.writeAll2(data, sink);
		//(4 - ok)
		//T = String, T를 자식으로 하는 모든 것이 다 됨.		
		//String str = Flusher.writeAll3(data,sink);ㅋ
		
		// 실습 내용은 상한이 있는 와일드 카드, 하한이 있는 와일드카드
	}
}
