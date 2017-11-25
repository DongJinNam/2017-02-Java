import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	/**/
	/*
	* Complete the function below.
	*/		
	static int zombieCluster(String[] zombies) {		 
		 int ans = 0;
		 int len = zombies.length; // 배열의 길이
		 // 방문 여부 초기화
		 boolean visited[] = new boolean[len];		 
		 for (int i = 0; i < len; i++) {			 
			 visited[i] = false;
		 }		 
		 for (int i = 0; i < len; i++) {
			 // 해당 점을 방문하지 않은 경우, 해당 점을 방문하고 bfs로 그래프를 탐색한다.
			 if (visited[i] == false) {
				 Queue<Integer> queue = new LinkedList<>();				 
				 queue.add(i);
				 visited[i] = true;				 
				 // Queue를 사용한 BFS
				 while (!queue.isEmpty()) {
					 int top = queue.peek();
					 queue.poll();
					 for (int j = 0; j < len; j++) {
						 if (visited[j] == false && zombies[top].charAt(j) == '1') {
							 queue.add(j);
							 visited[j] = true;
						 }
					 }
				 }				 
				 ans++;				 		
			 }
		 }
		 return ans;
	 }
/**/
	 public static void main(String[] args) throws IOException{
		 Scanner in = new Scanner(System.in);
		 int res;		
		 int _zombies_size = 0;
		 _zombies_size = Integer.parseInt(in.nextLine().trim());
		 String[] _zombies = new String[_zombies_size];
		 String _zombies_item;
		 for(int _zombies_i = 0; _zombies_i < _zombies_size; _zombies_i++) {
			 try {
				 _zombies_item = in.nextLine();
			 } catch (Exception e) {
				 _zombies_item = null;
			 }
			 _zombies[_zombies_i] = _zombies_item;
		 }		
		 res = zombieCluster(_zombies);
		 System.out.println(String.valueOf(res));
	 }
}