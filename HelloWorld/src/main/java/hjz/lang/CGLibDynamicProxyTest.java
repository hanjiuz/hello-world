package hjz.lang;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibDynamicProxyTest {
	
	public static class Hello {
		public void sayHello(){
			System.out.println("Hello World!");
		}
	}
	
	public static class CGLibDPMethoeInterceptor implements MethodInterceptor {
		
		@SuppressWarnings("unchecked")
		public <T> T getProxy(Class<T> cls) {
			return (T) Enhancer.create(cls, this);
		}

		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			
			System.out.println("Before CGLib dynamic proxy---");
			
			//call the original/super method on the specified object
			Object result = methodProxy.invokeSuper(object, args);
			
			System.out.println("After CGLib dynamic proxy---");
			
			return result;
			
		}
		
	}

	public static void main(String[] args) {
		CGLibDPMethoeInterceptor cglibMI = new CGLibDPMethoeInterceptor();
		Hello heloProxy = cglibMI.getProxy(Hello.class);
		heloProxy.sayHello();		

	}

}
