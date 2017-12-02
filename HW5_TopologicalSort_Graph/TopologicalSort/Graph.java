
import java.util.*;

public class Graph {

	private int V; // No.of Vertices
	private String vertice_str[];
	private List<Integer> adj[]; // Adjacency List.
	
	Graph(int v) {
		this.V = v;
		adj = new ArrayList[v];
		vertice_str = new String[v];
		for (int i = 0; i < v; i++)
			adj[i] = new ArrayList<>();
	}
	
	public void addEdge(int v,int w) {
		adj[v].add(w);
	}
	
	public void setVerticeName(int i,String name) {
		vertice_str[i] = name;
	}
	
	public void topologicalSort() {
		int indegree[] = new int[V];
		
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> temp = (ArrayList<Integer>) adj[i];
			for (int node : temp) {
				indegree[node]++;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}
		
		int cnt = 0; // Initialize count of visited vertices.
		
		Vector<Integer> topOrder = new Vector<Integer>();
		while (!q.isEmpty()) {
			int u = q.peek();
			q.poll();
			
			topOrder.add(u);
			
			for (int node : adj[u]) {
				if (--indegree[node] == 0)
					q.add(node);
			}
			cnt++;
		}
		
		if (cnt != V) {
			System.out.println("cycle exists!!");
			return;
		}
		
		for (int i : topOrder) {
			System.out.print(vertice_str[i] + " -> ");
		}
		System.out.println("Finish!!");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a graph given in the above diagram
        Graph g=new Graph(6);
        
        // 작업명 설정
        g.setVerticeName(0, "냄비에 물붓기");
        g.setVerticeName(1, "라면봉지 뜯기");
        g.setVerticeName(2, "점화");
        g.setVerticeName(3, "라면넣기");
        g.setVerticeName(4, "수프넣기");
        g.setVerticeName(5, "계란 풀어넣기");
        
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        
        g.topologicalSort();
	}

}
