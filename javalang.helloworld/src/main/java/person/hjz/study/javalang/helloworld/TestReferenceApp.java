package person.hjz.study.javalang.helloworld;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Hello world!
 *
 */
public class TestReferenceApp 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TestReferenceApp myApp = new TestReferenceApp();
        
        //Strong reference will never be garbage collected.
        //Soft reference will be garbage collected once memory is insufficient.
        //Weak reference will be queued first, than gc (regardless memory is sufficient or not).
        //Phantom reference is used for some cleanup operation before GCed, flexible than finalize() method.
        
        myApp.testSoftRef();
        
        myApp.testWeakRef();
        
        myApp.testPhantomRef();
        
             
        
    }
    
    public void testSoftRef() {
		Object obj = new Object();
		SoftReference<Object> softRef = new SoftReference<Object>(obj);
		
		//it is different to put obj or softRef to cache (e.g. HashSet)
		
		System.out.println(softRef.get());
		
		obj = null;
		System.out.println(obj);
		System.out.println(softRef.get());
		
		System.gc();
		
		System.out.println(softRef.get());
		
		
	}

	public void testWeakRef() {
    	Object obj = new Object();
    	ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
    	
    	WeakReference<Object> weakRef = new WeakReference<Object>(obj, refQueue);
    	
    	System.out.println(weakRef.get());
    	System.out.println(refQueue.poll());
    	
    	obj = null;
    	System.gc();
    	
    	System.out.println(weakRef.get());
    	System.out.println(refQueue.poll());
    	
    }
    
    public void testPhantomRef() {
    	Object obj = new Object();
    	ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
    	
    	PhantomReference<Object> phantomRef = new PhantomReference<Object>(obj, refQueue);
    	
    	System.out.println(phantomRef.get());
    	System.out.println(refQueue.poll());
    	
    	obj = null;
    	System.gc();
    	
    	System.out.println(phantomRef.get());
    	System.out.println(refQueue.poll());
    	
    }
}
