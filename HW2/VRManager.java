
import java.util.*;

public class VRManager {
	
	HashMap<Integer,Log> recordMap; // count + Log
	List<VRBaseTool> RentList; // 렌팅중인 Seating VR List
	List<VRBaseTool> RentList2; // 렌팅중인 Standing VR List
	List<VRBaseTool> RentList3; // 렌팅중인 Mobile VR List
	PriorityQueue<VRBaseTool> pq; // Seating VR Tool들을 담은 Priority Queue
	PriorityQueue<VRBaseTool> pq2; // Standing VR Tool들을 담은 Priority Queue
	PriorityQueue<VRBaseTool> pq3; // Mobile VR Tool들을 담은 Priority Queue
	int[] rentBoard; // 기기별로 rent 중인지 아닌지 체크함.
			
	public VRManager() {
		recordMap = new HashMap<Integer,Log>();		
		RentList = new ArrayList<>();
		RentList2 = new ArrayList<>();
		RentList3 = new ArrayList<>();
		pq = new PriorityQueue<>();
		pq2 = new PriorityQueue<>();
		pq3 = new PriorityQueue<>();
		rentBoard = new int[100];
	}
	
	public void Initialize() {
		// Seating VR Tool Setting
		for (int i = 0; i < 30; i++) {
			if (i < 15)
				pq.add(new VRSeatTool("OCC"));
			else
				pq.add(new VRSeatTool("PVR"));
		}
		// Standing VR Tool Setting		
		for (int i = 0; i < 30; i++) {			
			if (i < 10)			
				pq2.add(new VRStandTool("VIV"));
			else if (i >= 10 && i < 20)
				pq2.add(new VRStandTool("KIN"));
			else
				pq2.add(new VRStandTool("WII"));
		}
		// Mobile VR Tool Setting
		for (int i = 0; i < 40; i++) {			
			if (i < 10)			
				pq3.add(new VRMobileTool("SVR"));
			else if (i >= 10 && i < 20)
				pq3.add(new VRMobileTool("GCB"));
			else if (i >= 20 && i < 30)
				pq3.add(new VRMobileTool("VRB"));
			else
				pq3.add(new VRMobileTool("DLO"));
		}		
	}
	
	public PriorityQueue<VRBaseTool> getPQ(int type) {
		if (type == 1) {
			return pq;
		}
		else if (type == 2) {
			return pq2;
		}
		else {
			return pq3;
		}
	}
	
	public List<VRBaseTool> getList(int type) {
		if (type == 1) {
			return RentList;
		}
		else if (type == 2) {
			return RentList2;
		}
		else {
			return RentList3;
		}
	}	
	
	public HashMap<Integer,Log> getMap() {
		return recordMap;
	}
	
	public void setBoard(int i) {
		rentBoard[i] = 1 - rentBoard[i]; 
	}
	
	public int getBoard(int i) {
		return rentBoard[i];
	}
	
	// 출력 테스트 log
	public void printLog() {
		Set entrySet = recordMap.entrySet();
		Iterator it = entrySet.iterator();		
		while(it.hasNext()) {
			Map.Entry me = (Map.Entry)it.next();
			System.out.println("Case" + me.getKey() + " - Date : " + me.getValue());
		}
	}
	
	// 출력 테스트
	public void printAll() {
		// print seating VR tool
		PriorityQueue<? super VRBaseTool> tpq = new PriorityQueue<>();
		
		int i = 0;
		while (!pq.isEmpty()) {
			VRSeatTool s = (VRSeatTool) pq.peek();
			System.out.println("Case" + i + " : " + s.getCode());			
			pq.poll();
			tpq.offer(s);
			i++;
		}
		while (!tpq.isEmpty()) {
			VRSeatTool s = (VRSeatTool) tpq.peek();
			tpq.poll();
			pq.offer(s);			
		}
		while (!pq2.isEmpty()) {
			VRStandTool s = (VRStandTool) pq2.peek();
			System.out.println("Case" + i + " : " + s.getCode());			
			pq2.poll();
			tpq.offer(s);
			i++;
		}
		while (!tpq.isEmpty()) {
			VRStandTool s = (VRStandTool) tpq.peek();
			tpq.poll();
			pq2.offer(s);			
		}		
		while (!pq3.isEmpty()) {
			VRMobileTool s = (VRMobileTool) pq3.peek();
			System.out.println("Case" + i + " : " + s.getCode());			
			pq3.poll();
			tpq.offer(s);
			i++;
		}
		while (!tpq.isEmpty()) {
			VRMobileTool s = (VRMobileTool) tpq.peek();
			tpq.poll();
			pq3.offer(s);			
		}		
	}
	
}
