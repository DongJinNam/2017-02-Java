
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Graph {

	private int count; // 현재 그래프 상에서 존재하는 배우들의 인원 수
	private static int MAX_COUNT = 200000; // 최대 카운트
	private String vertice_str[]; // 배우들의 이름 배열
	private List<String> adj[]; // 해당 배우에 대한 연결된 배우들의 이름 정보들을 담고 있는 연결 리스트 배열
	private int degree[]; // 해당 배우에 대한 연결된 배우들의 인원 수 정보(Degree)들을 담은 배열
	private int maxDegCnt = 0; // Max Degree Count (최대 Degree를 가진 배우가 여러 명인 경우, 이름을 모두 출력하기 위해서 만들었습니다.)
	
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
		
	// 각 정점 별 degree 출력, max degree, avg degree 출력
	public void print() {
        // max degree 출력        		
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
	// 가장 큰 degree를 가진 배우이름을 출력
	public String maxDegree(int max) {		
		String name = "";		
		for (int i = 0; i < count; i++) {
			if (max == degree[i]) {
				name += getName(i);
			}
		}
		return name;
	}
	
	// 모든 배우들의 degree의 평균을 출력
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
        //"Movies.txt 입력"
        String content = new String(Files.readAllBytes(Paths.get("Movies.txt")));
        String data[] = content.split("\n");
        
        for (int i = 0; i < data.length; i++) {
        	String text = data[i];
        	String actor[] = text.split("/");
        	ArrayList<Integer> actorList = new ArrayList<>();
        	for (int j = 1; j < actor.length; j++) {
    			// 리스트에 이름이 없는 경우, 추가
        		//String name[] = actor[j].split(" ");
    			if (g.contains(actor[j]) == false) {
    				g.setName(actor[j]);
    			}        			
    			// 리스트에 있는 이름의 인덱스를 가져오기
    			int index = g.getIndex(actor[j]);        			
    			if (index >= 0) {
    				// actor list에 인덱스 추가.
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
        // 각 배우에 대한 degree 출력, max degree 출력, average degree 출력
        g.print();
	}

}
