
import java.util.*;

public class Log {
	Date when; // 언제
	String who; // 누가
	String code; // 대여한 VR Tool Code
	boolean bBorrow; // 대여이면 true, 반납이면 false
	int count; // 해당 VR기기 대여회수
	
	public Log() {}
	
	public Log(Date d, String wh, String c, boolean b, int cnt) {
		when = d;
		who = wh;
		code = c;
		bBorrow = b;
		count = cnt;
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
			return when + ", Count : " + Integer.toString(count) + ", VRCode :" + code + ", isRenting : " + bBorrow;
		else						
			return when + ", Who : " + who + ", VRCode : " + code + ", isRenting : " + bBorrow;
	}
}
