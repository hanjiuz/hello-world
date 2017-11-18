package hjz.lang;

public class AssertionTest {
	
	public static void main(String[] args) {
		
		try {
			//assert boolean : argument
			assert Math.random() > 0.5d : "Hello, something happen!";
		} catch (AssertionError ae) {
			
			//AssertionError throws. errors need not declare in throws clause but is also catchable
			System.out.println("something happen but caught!");
		}
		
		assert Math.random() < 0.5d : "Hello, something happen and not caught!";
		
		//assert(boolean) without argument
		assert(Math.random() == 0.5d);
		
		System.out.println("lucky, nothing happen!");	

	}	

}
