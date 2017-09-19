
public abstract class TwoDimensionalShape extends Shape {

	private double dimension1, dimension2;
	
	public TwoDimensionalShape(int x,int y,double d1,double d2) {
		super(x,y);
		dimension1 = d1;
		dimension2 = d2;
	}
	
	public void setDimension1(double d) {
		dimension1 = d;
	}
	
	public void setDimension2(double d) {
		dimension2 = d;
	}	
	
	public double getDimension1() {
		return dimension1;
	}
	
	public double getDimension2() {
		return dimension2;
	}	
		
	public abstract double area();
}
