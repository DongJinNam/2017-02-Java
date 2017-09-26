
import java.util.Scanner;

public class getPiValue {

	/**
	 * @param args
	 */
	
	double piValue(int loop) {		
		double ans = 0.0;
		int i;		
		for (i = 1; i <= loop; i++) {
			if (i % 2 == 0) {
				ans -= (double) (4.0 / (2*i-1));
			}
			else {
				ans += (double) (4.0 / (2*i-1));
			}
		}		
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getPiValue main = new getPiValue();
		Scanner sc = new Scanner(System.in);
		int loop;
		double ans;
		loop = sc.nextInt();
		ans = main.piValue(loop);
		System.out.println(ans);
	}
}
