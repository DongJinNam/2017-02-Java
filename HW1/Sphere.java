
public class Sphere extends ThreeDimensionalShape {
	
	public Sphere(int x,int y,double radius) {
		super(x,y,radius,radius,radius);
	}

	@Override
	public double area() {
		return 4 * Math.PI * super.getDimension1() * super.getDimension1();
	}

	@Override
	public double volume() {
		return 4.0 / 3.0 * Math.PI * super.getDimension1() * super.getDimension1() * super.getDimension1();
	}

	@Override
	public String getName() {
		return "Sphere";
	}
	
	@Override
	public String toString() {
		return "class_Name : " + getName() + ", name/number : " + "³²µ¿Áø/12121477";
	}		
	
	public void setRadius(double radius) {
		super.setDimension1(radius);
	}

	public double getRadius() {
		return super.getDimension1();
	}
}
