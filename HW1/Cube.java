

public class Cube extends ThreeDimensionalShape {
	
	public Cube(int x,int y,double side) {
		super(x,y,side,side,side);
	}

	@Override
	public double area() {
		return 6 * super.getDimension1() * super.getDimension1();
	}

	@Override
	public double volume() {
		return super.getDimension1() * super.getDimension1() * super.getDimension1();
	}
	
	@Override
	public String getName() {
		return "Cube";
	}	

	// Ŭ���� �̸��� �ۼ��� �̸��� �й�
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "������/12121477";
	}	
	
	public void setSide(double side) {
		super.setDimension1(side);
	}
	
	public double getSide(double side) {
		return super.getDimension1();
	}	

}
