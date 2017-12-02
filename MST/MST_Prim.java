

public class MST_Prim{

	private static final int V = 7;
	
	int findMin( int key[], Boolean isV[] ){
		int min = Integer.MAX_VALUE;
		int minidx = -1;
		for(int v=0; v<V; v++){
			if(isV[v] == false && key[v] < min )
			{
				min = key[v];
				minidx = v;
			}
		}
		return minidx;
	}

	void primMST( int graph[][] )
	{
		int minWeight[] = new int[V];
		Boolean isVisited[] = new Boolean[V];
		int parent[] = new int[V];
		
		//Initialize
		for(int i=0;i<V;i++){
			minWeight[i] = Integer.MAX_VALUE; 
			isVisited[i] = false;
		}
		// 0번 노드를 시작으로 가정하면..
		parent[0] = -1; // 자기가 루트다..
		minWeight[0] = 0;
		
		
		for(int count = 0; count < V-1; count++){ // V-1 --> MST조건		
			// 가장 작은 가중치로 분기되어야 함..
			int u = findMin( minWeight, isVisited); 
			isVisited[u] = true;
			// 핵심
			for( int v = 0; v<V;v++)
			{
				// Prim의 핵심
				if( graph[u][v]!=0 && isVisited[v]==false && graph[u][v] < minWeight[v])
				{
					parent[v] = u;
					System.out.println("parent "+v+" = "+u);
					minWeight[v] = graph[u][v];
					System.out.println("minWeight "+v+" = "+minWeight[v]);
				}			
			}	
		}	
		printMST(parent, graph);
	}
	
	void printMST(int parent[], int graph[][]){
		System.out.println("Edge  Weight");
		for(int i=1; i<V;i++)
			System.out.println(parent[i]+" - "+i+" "+graph[i][parent[i]]);
	}
	
	public static void main(String[] args){
	
		int graph[][] = new int[][]{
			{0,8,9,11,0,0,0},
			{8,0,0,0,10,0,0},
			{9,0,0,13,5,0,12},
			{11,0,13,0,0,8,0},
			{0,10,5,0,0,0,0},
			{0,0,0,8,0,0,7},
			{0,0,12,0,0,7,0}
		};
	
		MST_Prim prim = new MST_Prim();
		
		prim.primMST(graph);
	}


}