
public class ShapeTest {

	private Shape shapeArray[];
	
	public ShapeTest() {
		shapeArray = new Shape[6];
		
		shapeArray[0] = new Circle(22,88,1.25);
		shapeArray[1] = new Square(71,96,2.5);
		shapeArray[2] = new Sphere(8,89,3.75);
		shapeArray[3] = new Cube(79,61,5.0);
		shapeArray[4] = new Circle(22,88,3.5);
		shapeArray[5] = new Cube(71,96,3.0);
	}
	
	public void displayShapeInfo() {
		for (int i = 0; i < shapeArray.length; i++) {
			if (shapeArray[i] instanceof TwoDimensionalShape) {
				TwoDimensionalShape current = (TwoDimensionalShape) shapeArray[i];
				System.out.println(shapeArray[i]);
				System.out.printf("%s's area is %.2f\n",current.getName(),current.area());
			}
			if (shapeArray[i] instanceof ThreeDimensionalShape) {
				ThreeDimensionalShape current = (ThreeDimensionalShape) shapeArray[i];
				System.out.println(shapeArray[i]);
				System.out.printf("%s's area is %.2f\n%s's volume is %.2f\n",current.getName(),current.area(),
						current.getName(),current.volume());				
			}
		}
		// shapeArray 내에서 x,y좌표가 같은 도형을 출력합니다.
		for (int i = 0; i < shapeArray.length; i++) {
			for (int j = i+1; j < shapeArray.length; j++) {
				if (shapeArray[i].equals(shapeArray[j])) {
					// i번째 도형과 j번째 도형의 x,y좌표가 같으면 아래와 같은 메시지를 출력합니다.
					System.out.println("The " + i + "th and " + j + "th shapes have the same x and y coordinates.");
				}
			}
		}
	}		
	
	public static void main(String[] args) {		
		ShapeTest driver = new ShapeTest();
		driver.displayShapeInfo();
	}

}
