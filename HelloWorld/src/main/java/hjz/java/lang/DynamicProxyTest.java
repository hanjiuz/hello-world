package hjz.java.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
	
	interface IIHello {
		void sayIHello();
	}
	
	interface IHello {
		void sayHello();
	}
	
	static class Hello implements IIHello, IHello {
		public void sayHello() {
			System.out.println("Hello World!");
		}

		public void sayIHello() {
			System.out.println("Super I Hello World!");		
		}
	}
	
	static class MyDPInvokationHandler implements InvocationHandler {
		
		Object originalObj;
		
		Object bind(Object originalObj) {
			this.originalObj = originalObj;
			
			
			/*
			 * the bind() methods return an instance of a dynamically generated proxy class
			 * this dynamic proxy class extends from Proxy class and implemented all the interfaces of proxyed object
			 * this dynamic proxy class also holds a reference to InvocationHandler object which implements invoke() method.
			 * h.invoke() method will be called by dynamic proxy instance for every method calls of proxyed object.
			 */
			return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
			
		}

		
		/*
		 * this is the actual business logic to be put in proxy, the generated dynamic proxy class is just a wrapper.
		 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
		 */
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("hjz welcome from dynamic proxy for: " + method.getName());
			return method.invoke(originalObj, args);
		}
		
	}
	

	public static void main(String[] args) throws Throwable {
		
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
		
		IHello hello = (IHello) new MyDPInvokationHandler().bind(new Hello());
		hello.sayHello();
		hello.toString();
		hello.hashCode();
		hello.equals(new Object());
		
		IIHello superHello = (IIHello) hello;
		superHello.sayIHello();
		
		System.out.println(new File("$Proxy0" + ".class").getAbsolutePath()); 	
		
	}

}
