
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Random rand = new Random(); // 난수 생성을 위한
		List<VRBaseTool> list;
		VRSeatTool seat;
		VRStandTool std;
		VRMobileTool mobile;
		
		int i,j,q;
		int typeNum, toolNum; // 입력받을 VR Type number, 입력받을 VR Tool number
		int totalCnt = 0; // 총 연산 회수
		String name; // 대여자 이름 input
		
		VRManager manager = new VRManager(); // VR 기기 관리 매니저
		manager.Initialize(); // management Initialize		
								
		while(true) {
			
			System.out.println("대여을 하시려면 1을 입력, 반납을 하시려면 2을 입력하십시오. 로그 출력은 3. 프로그램 종료는 0을 입력");			
			q = input.nextInt();			
			if (q == 0) break; // 프로그램 종료
			
			if (q == 1) {				
				System.out.println("1 : Seating VR, 2 : Standing VR, 3 : Mobile VR, 4 : Random");
				System.out.printf("어떤 종류의 기기를 대여하시겠습니까. 번호를 입력하세요 : ");
				typeNum = input.nextInt();
				input.nextLine();
				
				System.out.printf("대여자 이름을 입력하세요 : ");
				name = input.nextLine();
				
				if (typeNum == 4) {
					typeNum = rand.nextInt(3) + 1;					
				}
				
				switch(typeNum) {
				case 1:		
					seat = (VRSeatTool) manager.getPQ(typeNum).peek();
					manager.getPQ(typeNum).poll();
					manager.getList(typeNum).add(seat);
					manager.getMap().put(totalCnt++, new Log(new Date(),name,seat.getCode(), true)); // log 기록
					manager.setBoard(seat.getSN());
					break;
				case 2:
					std = (VRStandTool) manager.getPQ(typeNum).peek();
					manager.getPQ(typeNum).poll();
					manager.getList(typeNum).add(std);
					manager.getMap().put(totalCnt++, new Log(new Date(),name,std.getCode(), true)); // log 기록
					manager.setBoard(std.getSN());
					break;
				case 3:
					mobile = (VRMobileTool) manager.getPQ(typeNum).peek();
					manager.getPQ(typeNum).poll();
					manager.getList(typeNum).add(mobile);
					manager.getMap().put(totalCnt++, new Log(new Date(),name,mobile.getCode(), true)); // log 기록
					manager.setBoard(mobile.getSN());
					break;					
				}
				
			}
			else if(q == 2) {
				System.out.printf("반납할 기기 번호를 입력하시오 : ");
				toolNum = input.nextInt();
				
				if (manager.getBoard(toolNum) == 0) {
					System.out.println("현재 대여중이지 않는 기기입니다.");
					continue;
				}

				if (toolNum >= 90)				
					list = manager.getList(3);
				else
					list = manager.getList(toolNum / 30 + 1);
				
				for (i = 0; i < list.size(); i++) {					
					if (toolNum / 30 < 1) {
						seat = (VRSeatTool) list.get(i);
						if (seat.getSN() == toolNum) {
							list.remove(i);
							seat.incCount(); // 반납 시 카운트 증가
							manager.getPQ(1).offer(seat);
							manager.getMap().put(totalCnt++, new Log(new Date(), "", seat.getCode(), false)); // log 기록
							break;
						}
					}
					else if (toolNum / 30 < 2) {
						std = (VRStandTool) list.get(i);
						if (std.getSN() == toolNum) {
							list.remove(i);
							std.incCount(); // 반납 시 카운트 증가
							manager.getPQ(2).offer(std);
							manager.getMap().put(totalCnt++, new Log(new Date(), "", std.getCode(), false)); // log 기록
							break;
						}						
					}
					else {
						mobile = (VRMobileTool) list.get(i);
						if (mobile.getSN() == toolNum) {
							list.remove(i);
							mobile.incCount(); // 반납 시 카운트 증가
							manager.getPQ(3).offer(mobile);
							manager.getMap().put(totalCnt++, new Log(new Date(), "", mobile.getCode(), false)); // log 기록
							break;
						}						
					}
				}								
			}
			else if(q == 3)
				manager.printLog();
		}	
	}
}
