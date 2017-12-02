
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {

	private int count; // ���� �׷��� �󿡼� �����ϴ� ������ �ο� ��
	private static int MAX_COUNT = 200000; // �ִ� ī��Ʈ
	private String vertice_str[]; // ������ �̸� �迭
	private List<String> adj[]; // �ش� ��쿡 ���� ����� ������ �̸� �������� ��� �ִ� ���� ����Ʈ �迭
	private int degree[]; // �ش� ��쿡 ���� ����� ������ �ο� �� ����(Degree)���� ���� �迭
	private int maxDegCnt = 0; // Max Degree Count (�ִ� Degree�� ���� ��찡 ���� ���� ���, �̸��� ��� ����ϱ� ���ؼ� ��������ϴ�.)
	
	public Graph() {
		count = 0;
		adj = new ArrayList[MAX_COUNT];
		vertice_str = new String[MAX_COUNT];
		for (int i = 0; i < MAX_COUNT; i++)
			adj[i] = new ArrayList<>();
		degree = new int[MAX_COUNT];
		maxDegCnt = 0;
	}
	
	public void addEdge(int v,String name) {
		adj[v].add(name);	
	}
		
	public boolean contains(String name) {		
		for (int i = 0; i < count; i++) {
			if (vertice_str[i].compareTo(name) == 0) return true;
		}
		return false;
	}
	
	public int getIndex(String name) {
		for (int i = 0; i < count; i++) {
			if (vertice_str[i].compareTo(name) == 0) return i;
		}
		return -1;		
	}	
	
	public void setName(String name) {
		vertice_str[count++] = name;
	}
	
	public String getName(int idx) {
		return vertice_str[idx];
	}
		
	// �� ���� �� degree ���, max degree, avg degree ���
	public void print() {
        // max degree ���        		
		for (int i = 0; i < count; i++) {
			degree[i] = degree(getName(i));
			if (maxDegCnt < degree[i]) maxDegCnt = degree[i];
			System.out.println(getName(i) + "'s Degree : " + degree[i]);			
		}
        System.out.println("Max Degree : " + maxDegree(maxDegCnt));
        System.out.println("Average Degree : " + avgDegree());
	}	
	
	public int degree(String name) {
		int index = getIndex(name);
		if (index >= 0)
			return adj[index].size();
		return -1;
	}	
	// ���� ū degree�� ���� ����̸��� ���
	public String maxDegree(int max) {		
		String name = "";		
		for (int i = 0; i < count; i++) {
			if (max == degree[i]) {
				name += getName(i);
			}
		}
		return name;
	}
	
	// ��� ������ degree�� ����� ���
	public double avgDegree() {
		int total = 0;		
		double rtn;
		for (int i = 0; i < count; i++) {
			total += adj[i].size();
		}
		rtn = (double) total / count;
		return rtn;
	}
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Create a graph given in the above diagram
        Graph g = new Graph();
        //"Movies.txt �Է�"
        String content = new String(Files.readAllBytes(Paths.get("Movies.txt")));
        String data[] = content.split("\n");
        
        for (int i = 0; i < data.length; i++) {
        	String text = data[i];
        	String actor[] = text.split("/");
        	ArrayList<Integer> actorList = new ArrayList<>();
        	for (int j = 1; j < actor.length; j++) {
    			// ����Ʈ�� �̸��� ���� ���, �߰�
        		//String name[] = actor[j].split(" ");
    			if (g.contains(actor[j]) == false) {
    				g.setName(actor[j]);
    			}        			
    			// ����Ʈ�� �ִ� �̸��� �ε����� ��������
    			int index = g.getIndex(actor[j]);        			
    			if (index >= 0) {
    				// actor list�� �ε��� �߰�.
    				actorList.add(index);
    			}             		
        	}
       		// Graph connection
    		for (int i0 = 0; i0 < actorList.size(); i0++) {
    			for (int i1 = i0+1; i1 < actorList.size(); i1++) {
    				int val0 = actorList.get(i0);
    				int val1 = actorList.get(i1);    				
    				g.addEdge(val0, g.getName(val1));
    				g.addEdge(val1, g.getName(val0));    				
    			}
    		}          	
        } 
        // �� ��쿡 ���� degree ���, max degree ���, average degree ���
        g.print();
	}

}
