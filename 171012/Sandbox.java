import java.util.Scanner;
// TODO 1 : make Thread or Runnable out of this class
// Hint : Thread class or Runnable interface
class Worker implements Runnable {
	private int value;
	public Worker(int value) {
		this.value = value;
	}
	public void run() {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(value);
	}
}
// TODO 3 : Define NumberBoundException
class NumberBoundException extends Exception { 
	public NumberBoundException() {
		// TODO Auto-generated constructor stub	
	}	
	public String getMessage() {
		return "NumberBoundException";
	}
}

public class Sandbox {
	public static void main(String[] args) {
		int[] arr = null;
		// TODO 4 : Catch NumberBoundException at input() function.
		// If exception occurred, Print 'NumberBoundException' and finish program
		// Hint : 'return' will finish main() function		
		try {
			arr = input();
			for(Integer n : arr) {
				// TODO 2 : run Worker class
				Worker work = new Worker(n);
				Thread th = new Thread(work);
				th.start();
			}			
		} catch (NumberBoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public static int[] input() throws NumberBoundException {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];
		for(int cnt = 0; cnt < N; cnt++) {
			arr[cnt] = s.nextInt();
			if(arr[cnt] > 1000) throw new NumberBoundException();
		}
		s.close();
		return arr;
	}
}