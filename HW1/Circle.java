
public class Circle extends TwoDimensionalShape {
	
	public Circle(int x,int y, double radius) {
		super(x,y,radius,radius);
	}

	@Override
	public double area() {			
		return Math.PI * super.getDimension1() * super.getDimension1();
	}

	@Override
	public String getName() {
		return "Circle";
	}
	
	// 클래스 이름과 작성자 이름과 학번
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "남동진/12121477";
	}	
	
	public void setRadius(double radius) {
		super.setDimension1(radius);
	}
	
	public double getRadius() {
		return super.getDimension1();
	}

}
