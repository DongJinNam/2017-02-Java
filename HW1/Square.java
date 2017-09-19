
public class Square extends TwoDimensionalShape {

	public Square(int x,int y,double side) {
		super(x,y,side,side);
	}
	
	@Override
	public double area() {
		return super.getDimension1() * super.getDimension1();
	}

	@Override
	public String getName() {
		return "Square";
	}
	
	// Ŭ���� �̸��� �ۼ��� �̸��� �й�
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "������/12121477";
	}		
	
	public void setSide(double side) {
		super.setDimension1(side);
	}
	
	public double getSide() {
		return super.getDimension1();
	}

}
