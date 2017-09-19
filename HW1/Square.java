
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
	
	// 클래스 이름과 작성자 이름과 학번
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "남동진/12121477";
	}		
	
	public void setSide(double side) {
		super.setDimension1(side);
	}
	
	public double getSide() {
		return super.getDimension1();
	}

}
