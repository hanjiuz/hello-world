package hjz.lang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JDK5NewFeaturesTest {

	public static void main(String[] args) {
		
		
		System.out.println(System.getProperty("java.home"));
		System.out.println(System.getenv("PATH"));
		System.out.println(System.getenv("JAVA_HOME"));
		
		//generic types - Integer as a type parameter passed to List<T>
		//various length parameter - Arrays.asList(T...)
		//auto boxing - 1,2,3,4,5 will be boxed to Integer.valueOf(1)...
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
		int sum = 0;
		
		//for each loop
		//auto unboxing - list element Integer assigned to int variable: i = (Integer)list.get(i).intValue()
		for(int i : list) {
			sum += i;
		}
		
		System.out.println(sum);
		
		
		//call main2() will get exactly same result
		main2(null);

	}
	
	
	//the above code will become below code after javac compile
	//generic type will become raw type and type parameter was wiped, force type tranform is needed
	//for each will become normal for loop with iterator looping
	//various length parameters will become Array
	//boxing and unboxing
	public static void main2(String[] args) {
		
		List list = Arrays.asList(new Integer[]{
				Integer.valueOf(1),
				Integer.valueOf(2),
				Integer.valueOf(3),
				Integer.valueOf(4),
				Integer.valueOf(5),
		});
		
		int sum = 0;		
		for(Iterator iterator = list.iterator(); iterator.hasNext();) {
			int i = ((Integer)iterator.next()).intValue();
			sum += i;
		}
		System.out.println(sum);
		
	}

}
