
public class VRStandTool extends VRBaseTool {

	static int VRStdBase = 30;
	
	public VRStandTool(String s) {
		super(1,s);
		serial_number = VRStdBase++;
		
		code += "STD";
		code += s;
		code += serial_number;
		
		setUsage("useful for arcade games and body games."); //
	}
	
}
