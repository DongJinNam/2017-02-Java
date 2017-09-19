
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
	
	// Ŭ���� �̸��� �ۼ��� �̸��� �й�
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "������/12121477";
	}	
	
	public void setRadius(double radius) {
		super.setDimension1(radius);
	}
	
	public double getRadius() {
		return super.getDimension1();
	}

}
