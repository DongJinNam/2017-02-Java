

// 1. Thread를 상속받아서 Thread Programming을 함.
// 이건 거의 안 쓴다.
class ThreadExtends extends Thread {
	public void run() {
		// 여기에 쓰레드를 구현할 로직을 넣으면 된다.
		for (int i = 0; i < 10; i++) {
			System.out.println(getName()); // 자신의 이름을 가져온다.
			yield(); // scheduler에게 힌트를 준다. current Thread가 양보한다.
		}
	}	
}


// 2. Runnable Interface를 구현, 주로 아래 주서과 같은 형태로 사용한다.
// class ThreadRunnable extends ParentClass implements Runnable
class ThreadRunnable implements Runnable {
	
	public void run() {
		// 여기에 쓰레드를 구현할 로직을 넣으면 된다.
		// 단순히 Runnable implements를 하는 경우, getName() compile Error
		// Thread.currentThread().getName(); 이건 static이 선언이 되어있다.
		// 상속을 받고, 안 받고 차이 왜 그런지 알아야 한다.
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()); // 자신의 이름을 가져온다.
			Thread.yield(); // scheduler에게 힌트를 준다. current Thread가 양보한다.
		}
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("main start");
		System.out.println(Thread.currentThread().getName()); // main thread 확인 가능		
				
		ThreadExtends te = new ThreadExtends();
		ThreadRunnable tr = new ThreadRunnable();
		//tr.start(); 이건 안된다. 이거 해결방법은 아래에 있다.
		Thread t = new Thread(tr); // Thread(Runnable) 형태의 생성자가 있다.
		// 왜 run을 안하고, start를 사용했는지.
		// start 함수가 scheduler에게 할당받을 준비하는 작업을 하기 때문이다. new -> Runnable -> Scheduler -> run -> running -> terminate
		// 슬라이드 내 참고
		t.start();// run 함수 실행	
		te.start(); // thread 시작, run 함수 실행

		// join 함수 - 메인 함수 수명을 길게 한다. (worker thread가 끝날 때까지 main thread는 기다린다)
		// thread join 사용 시에는 try catch를 꼭 사용해야 한다.
		try {
			te.join();
			t.join();
		}
		catch (InterruptedException e) {
			System.out.println("Interrupted Exception Catched");
		}				
		System.out.println("main end");		
	}

}
