package hjz.java.lang;

public class StaticProxyTest {
	
	interface Hello {
		void sayHello();
	}
	
	static class HelloImpl implements Hello {

		public void sayHello() {
			System.out.println("Hello World!");
			
		}
	}
	
	static class HelloProxy implements Hello {
		private Hello hello;
		
		public HelloProxy() {
			hello = new HelloImpl();
		}
		
		public void sayHello() {
			//do something before
			System.out.println("Before...");
			
			hello.sayHello();
			
			//do something after
			System.out.println("After...");
		}
	}

	public static void main(String[] args) {
		Hello hello = new HelloProxy();
		hello.sayHello();
	}

}
