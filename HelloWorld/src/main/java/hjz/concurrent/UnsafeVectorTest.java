package hjz.concurrent;

import java.util.Vector;

public class UnsafeVectorTest {
	
	private static Vector<Integer> vector = new Vector<Integer>();

	
	/*
	 * This program might throw exception because index out of bound
	 * every single method of Vector is safe, but a serial of method flows might not be safe.
	 */
	public static void main(String[] args) {
		
		while(true) {
			for(int i=0; i<10; i++) {
				//auto boxing
				vector.add(i);
			}
			
			Thread removeThread = new Thread(new Runnable() {
							public void run() {
								for(int i=0; i<vector.size(); i++) {
									vector.remove(i);
								}
							}
						}
					);
			
			Thread printThread = new Thread(new Runnable() {
							public void run() {
								for(int i=0; i<vector.size(); i++) {
									System.out.println(vector.get(i));
								}
							}
						}
					
					);
			
			removeThread.start();
			printThread.start();
			
			//avoid too much active threads
			while(Thread.activeCount() > 20);
			
		}
		
		//Interesting that even some thread throws exceptions, the program keep running
		//because only that error thread was skilled by the exception.
		//even you use try...catch... in the main method, you can not cache other thread's exception
		//because you can only catch the main thread's exception.

	}

}
