package hjz.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class DynamicProxyTest0 {
	
	interface Hello {
		void sayHello();
	}
	
	interface IHello {
		void sayIHello();
	}
	
	static class HelloImpl implements Hello, IHello {

		public void sayHello() {
			System.out.println("Hello World!");
			
		}
		
		public void sayIHello() {
			System.out.println("I Hello World!");
		}
	}
	
	static class HelloDPInvocationHandler implements InvocationHandler {
		
		private Object target;
		
		public HelloDPInvocationHandler(Object target) {
			Objects.requireNonNull(target);
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			//do something special
			System.out.println("Greeting from James: ");
			
			return method.invoke(target, args);

		}
		
	}
	

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		

		//create a InvocationHandler instance and inject the target object
		InvocationHandler handler = new HelloDPInvocationHandler(new HelloImpl());
		
		
//		//get dynamic proxy class which is generated automatically
//		//Class<?> dynamicProxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), new Class<?>[]{Hello.class});
//		Class<?> dynamicProxyClass = Proxy.getProxyClass(Hello.class.getClassLoader(), Hello.class, IHello.class);		
//		//use the handler instance to instantiate a proxy instance
//		Hello hello = (Hello) dynamicProxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
		
		
		//same as above, create a proxy instance directly
		Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class<?>[]{Hello.class, IHello.class}, handler);
		
		
		//call the sayHello() method from proxy instance, so it will call handler's invoke() method which will call target object corresponding method.
		hello.sayHello();
		
		IHello ihello = (IHello) hello;
		ihello.sayIHello();
		
	}

}
