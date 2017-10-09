
import java.util.*;

public class Log {
	Date when; // 언제
	String who; // 누가
	String code; // 대여한 VR Tool Code
	boolean bBorrow; // 대여이면 true, 반납이면 false
	
	public Log() {}
	
	public Log(Date d, String wh, String c, boolean b) {
		when = d;
		who = wh;
		code = c;
		bBorrow = b;
	}	
	
	public void setDate(Date d) {
		when = d;
	}
	
	public void setWho(String w) {
		who = w;
	}
	
	public void setBorrow(boolean b) {
		bBorrow = b;
	}
	
	public Date getDate() {
		return when;
	}
	
	public String getWho() {
		return who;
	}
	
	public String getCode() {
		return code;
	}
	
	public String toString() {		
		if (bBorrow == false)
			return when + ", VRCode : " + code + ", isRenting : " + bBorrow;
		else						
			return when + ", Who : " + who + ", VRCode : " + code + ", isRenting : " + bBorrow;
	}
}
