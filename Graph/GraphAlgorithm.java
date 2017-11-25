import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class GraphAlgorithm {

	static int vNum = 8; 
	static int eNum = 11;
	static int[][] adjacencyMatrix = {
		{0,1,1,1,0,0,0,0},
		{1,0,1,0,0,0,0,0},
		{1,1,0,1,1,0,0,0},
		{1,0,1,0,0,1,1,0},
		{0,0,1,0,0,0,0,0},
		{0,0,0,1,0,0,1,1},
		{0,0,0,1,0,1,0,1},
		{0,0,0,0,0,1,1,0}	
	};
	
	static int[][] adjacencyMatrix2 = 
  { {0,1,0,0,0,1,0,1},
  	{1,0,1,0,1,1,0,0},
  	{0,1,0,1,1,0,0,0},
  	{0,0,1,0,1,0,0,0},
  	{0,1,1,1,0,0,0,0},
  	{1,1,0,0,0,0,1,1},
  	{0,0,0,0,0,1,0,0},
  	{1,0,0,0,0,1,0,0}
  }; // ������� ;
	
	
	static boolean[] isV;

	public static void main(String [] args)
	{
		
		BFS(1);
		
		DFS_stack(1);
		
		isV = new boolean[vNum];
		
		for(int i = 0; i<vNum; i++)
				isV[i] = false;
			
		System.out.println("���ȣ���� �̿��� DFS");
		DFS_recursive(1);
	}

	public static void DFS_recursive(int v)
	{
		isV[v-1] = true;
		for(int i=1;i<=vNum;i++)
		{
			if(adjacencyMatrix2[v-1][i-1] == 1 && !isV[i-1]){
				System.out.println( v+" ���� " + i + "�� �̵�");
				DFS_recursive(i);
			}
		}
	}

	public static void DFS_stack(int v)
	{
		System.out.println("Stack�� �̿��� DFS");
		//(1)Stack ����
		Stack<Integer> sstack = new Stack<Integer>();
		//(2) isVisited ���� �� �ʱ�ȭ
		boolean[] isVisited = new boolean[vNum];
		for(int i = 0; i<vNum; i++)
				isVisited[i] = false;		
		//(3) isPushed ���� �� �ʱ�ȭ
		boolean[] isPushed = new boolean[vNum];
		for(int i = 0; i<vNum; i++)
				isPushed[i] = false;
				
		sstack.push(v-1);
		
		while(!sstack.isEmpty()){
			v = sstack.pop();
			if(isVisited[v] == false){
				System.out.println( v+1 + "�� �̵�");
				isVisited[v] = true;
				for(int i=vNum-1; i>=0; i--)
				{
					if(adjacencyMatrix2[v][i] == 1 && !isVisited[i]){
							if(isPushed[i]==false)
							{
								sstack.push(i); // push
								isPushed[i]=true;
							}
					}			
				}				
			}		
		}	
	}
	
	public static void BFS(int v)
	{
		//(1)ť ����
		Queue queue = new LinkedList<Integer>();
		//(2) isVisited ���� �� �ʱ�ȭ
		boolean[] isVisited = new boolean[vNum];
		for(int i = 0; i<vNum; i++)
				isVisited[i] = false;
		
		isVisited[v-1] = true;
		queue.add(v);	// enqueue
		
		while( !queue.isEmpty() )
		{
			v = (int) queue.poll(); // dequeue
			System.out.println( v + "�� �̵�");
			for(int i=1; i<=vNum; i++)
			{
				if(adjacencyMatrix[v-1][i-1] == 1 && !isVisited[i-1]){						
						queue.add(i); // enqueue
						isVisited[i-1] = true;
				}			
			}		
		}
	}
}