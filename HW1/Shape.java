
public abstract class Shape {
	private int x,y;
	
	public Shape(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	// overriding (매개변수 객체의 x,y좌표와 현 객체의 x,y좌표 값이 모두 같은 경우 true return)
	public boolean equals(Shape other) {
		if (this.x == other.getX() && this.y == other.getY()) return true;
		else return false;
	}	
	
	public abstract String getName();
	public abstract String toString();
}
