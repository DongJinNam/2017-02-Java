
import java.lang.*;

public class VRBaseTool implements VRTool, Comparable<VRBaseTool> {
	
	String code; // VRTool code
	String brand; // VRTool brand
	String usage; // how to play use
	int count;
	int type; // 1 : Seating, 2 : Standing, 3 : Mobile
	int serial_number; // VRTool serial number
	
	public VRBaseTool() {
		count = 0;
	}
	
	public VRBaseTool(int t,String s) {
		type = t;
		count = 0;
		brand = s;
		code = "";
	}
	
	public void setUsage(String u) {
		usage = u;
	}	

	public int getSN() {
		return serial_number;
	}
			
	@Override
	public String getCode() {
		return code;
	}
	
	@Override
	public String whatPlay() {
		return usage;
	}	
	
	@Override
	public void incCount() {
		count++;
	}	

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public int getType() {
		return type;
	}
	
	// priority queue에서의 비교
	@Override
	public int compareTo(VRBaseTool target) {		
		if (this.count > target.count)
			return 1;
		else if (this.count == target.count && this.serial_number > target.serial_number)
			return 1;
		else
			return -1;		
	}
		
}
