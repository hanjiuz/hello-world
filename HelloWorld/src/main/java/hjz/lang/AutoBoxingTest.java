package hjz.lang;

public class AutoBoxingTest {

	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		
		int i = 0b1010;
		
		System.out.println(c == d);  //true - dose not unbox, but true for same Integer(3) object
		System.out.println(e == f);  //false - why false? why not same Integer(321) object?
		System.out.println(c == (a + b)); //true - auto unbox because of match calculation
		System.out.println(c.equals(a + b)); //true - auto unbox and box 
		System.out.println(g == (a + b)); //true - auto unbox because of match calculation
		System.out.println(g.equals(a + b)); //false - auto unbox and box, but equals() will not convert type automatically

	}

}
