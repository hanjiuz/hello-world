package hjz.concurrent;

import junit.framework.TestCase;

public class GrumpyBoundedBufferTest extends TestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrumpyBoundedBufferTest test = new GrumpyBoundedBufferTest();
		
		test.setUp();
		test.testBoundedBufferPut();
		test.tearDown();
		
		test.setUp();
		test.testBoundedBufferTake();
		test.tearDown();
	}
	
	GrumpyBoundedBuffer<Integer> gbBuffer;
	Integer[] products;
	
	public void setUp() {
		gbBuffer = new GrumpyBoundedBuffer<Integer>(100);
		
		products = new Integer[100];
		for(int i=0; i<100; i++) {
			products[i] = new Integer(i);
		}
		
	}
	
	public void tearDown() {
		gbBuffer = null;
		
		products = null;
	}	
		
	
	public void testBoundedBufferPut() {
		
		try {
			for(Integer i: products) {
					gbBuffer.put(i);				
			}
			
			//put 101th will trigger BufferFullException
			gbBuffer.put(new Integer(101));	
			
		} catch (BufferFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testBoundedBufferTake() {
		
		try {
			for(Integer i: products) {
					gbBuffer.put(i);				
			}
		} catch (BufferFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			for(int i=0; i<100; i++) {
				gbBuffer.take();
			}
			
			//take 101th will trigger BufferEmptyException
			gbBuffer.take();
			
		} catch (BufferEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
	}

}
