
public abstract class ThreeDimensionalShape extends Shape {

	private double dimension1, dimension2, dimension3;
	
	public ThreeDimensionalShape(int x,int y,double d1,double d2,double d3) {
		super(x,y);
		dimension1 = d1;
		dimension2 = d2;
		dimension3 = d3;
	}
	
	public void setDimension1(double d) {
		dimension1 = d;
	}
	
	public void setDimension2(double d) {
		dimension2 = d;
	}
	
	public void setDimension3(double d) {
		dimension3 = d;
	}		
	
	public double getDimension1() {
		return dimension1;
	}
	
	public double getDimension2() {
		return dimension2;
	}	

	public double getDimension3() {
		return dimension3;
	}	
	
	public abstract double area();
	public abstract double volume();
}
