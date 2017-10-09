
public class VRMobileTool extends VRBaseTool {

	static int VRMobileBase = 70;
		
	public VRMobileTool(String s) {
		super(1,s);
		serial_number = VRMobileBase++;
		code += "MOB";
		code += s;		
		code += serial_number;
		
		setUsage("useful for educational games and mobility games."); //
	}	
	
}
