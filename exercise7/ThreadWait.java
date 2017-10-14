
class SharedData {
	private boolean isNew = false;
	private int data;
	public synchronized void put (int d) {		
		try {
			while(isNew) {
				System.out.println("put waiting()...");
				wait();
			}
		} catch (InterruptedException e) {}
		this.data = d;
		isNew = true;
		notifyAll();
		System.out.println("put notifyall()...");		
	}
	public synchronized int get() {
		try {
			while(!isNew) {
				System.out.println("get waiting()...");
				wait();
			}
		} catch (InterruptedException e) {}
		isNew = false;
		notifyAll();
		System.out.println("get notifyall()...");
		return data;
	}
}

class Producer extends Thread {
	private SharedData shared;
	private int data;
	public Producer(SharedData shared, int data) {
		this.shared = shared;
		this.data = data;
	}
	public void run() {
		System.out.println("Put " + data);
		shared.put(data);
		System.out.println("Put " + (data+1));
		shared.put(data+1);		
	}
}

class Consumer extends Thread {
	private SharedData shared;
	public Consumer(SharedData shared) {
		this.shared = shared;
	}
	public void run() {
		while(true)
			System.out.println(getName() + " got " + shared.get());
	}
}

public class ThreadWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SharedData shared = new SharedData();
		new Consumer(shared).start();
		new Consumer(shared).start();
		new Producer(shared,10).start();
		new Producer(shared,20).start();
	}

}
