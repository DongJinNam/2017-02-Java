
public class VRSeatTool extends VRBaseTool {
			
	static int VRSeatBase = 0;	
	
	public VRSeatTool(String s) {
		super(1,s);
		serial_number = VRSeatBase++;
		
		code += "SET";
		code += s;		
		if (VRSeatBase <= 10)
			code += "0";
		code += serial_number;				
		setUsage("useful for 360 degree video games and RPG game."); //
	}
	
}
