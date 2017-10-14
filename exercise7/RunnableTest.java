
class Counter {
	public int count = 0;
	public synchronized void inc() {
		++count;
	}	
}

// Thread unsafe and safe
// 쓰레드 내에서 데이터가 망가지는지 안 망가지는지 차이
class UnsafeCounter implements Runnable {
	Counter counter;
	public UnsafeCounter (Counter c) {
		counter = c;
	}
	public void run() {
		for (int i = 0; i < 10000000; i++) {
			counter.inc();
		}
	}
}

public class RunnableTest implements Runnable {

	long delay;
	String msg;
	
	RunnableTest(String m,long l) {
		msg = m;
		delay = l;
	}
	
	public void run() {
		try {
			while(true) {
				System.out.print(msg + " ");
				Thread.sleep(delay);
			}
		}
		catch (InterruptedException e) {return;}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test code
		/**
		RunnableTest r1 = new RunnableTest("Thread-r1", 50);
		RunnableTest r2 = new RunnableTest("Thread-r2", 100);
		
		new Thread(r1).start();
		new Thread(r2).start();
		*/
		
		Counter cc = new Counter();
		UnsafeCounter us1 = new UnsafeCounter(cc);
		UnsafeCounter us2 = new UnsafeCounter(cc);
		Thread th1 = new Thread(us1);
		Thread th2 = new Thread(us2);
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();			
		} catch (InterruptedException e) {
			System.out.println("Interrupt Happened");
		}								
		System.out.println(cc.count);
	}

}
