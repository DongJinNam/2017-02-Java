import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Do not modify Worker class
class Worker {
	private WorkStartListener workStartListener;
	private WorkFinishedListener workFinishedListener;
	
	public void start(int a, int b) {
		if(workStartListener != null) workStartListener.onWorkStart();
		int result = a + b;
 		System.out.println(a + " + " + b + " = " + result);
 		if(workFinishedListener != null) workFinishedListener.onWorkFinished(result);
	}
	public void registerWorkStartListener(WorkStartListener workStartListener) {
		this.workStartListener = workStartListener;
	}
	public void registerWorkFinishedListener(WorkFinishedListener workFinishedListener) {
		this.workFinishedListener = workFinishedListener;
	}
	interface WorkStartListener {
		public void onWorkStart();
	}
	interface WorkFinishedListener {
		public void onWorkFinished(int result);
	}
		
}

class WorkListener implements Worker.WorkStartListener, Worker.WorkFinishedListener {	
	public WorkListener() {
	
	}
	
	public void onWorkStart() {
		System.out.println("Working");
	}
	
	public void onWorkFinished(int r) {
		System.out.println(r);
	}
}




public class Problem {
	// TODO : 1. Make WorkListener class
	// TODO : 2. Register WorkListener in Worker
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final int a = scan.nextInt();
		final int b = scan.nextInt();
		scan.close();
		Worker worker = new Worker();
		// example : registering listener
		WorkListener workListener = new WorkListener();
		worker.registerWorkStartListener(workListener);
		worker.registerWorkFinishedListener(workListener);
		worker.start(a, b);		
	}
}