package hjz.concurrent;

public class MultipleThreadsTest {
	
	public static class MyThread extends Thread {
		
		private int ticket = 1000;
		
		public MyThread(String name) {
			super(name);
		}
		
		public void run() {	
			while(ticket > 0){
				System.out.println(ticket-- + " is sold by thread: " + this.getName());
			}
		}
	}
	
	public static class MyRunnable implements Runnable {
		
		private String name = "";
		private int ticket = 1000;
		
		public MyRunnable(String name) {
			this.name = name;
		}

		public void run() {
			while(ticket > 0){
				System.out.println(ticket-- 
						+ " is sold by thread: " + Thread.currentThread().getName() 
						+ " and runnable " + name);
			}
		}
		
	}
	

	public static void main(String[] args) {
		
		//test new thread from MyThread
		for(int i=0; i<3; i++) {
			new MyThread("Window-"+i).start();			
		}
		
		//test new thread from MyRunnable
		for(int i=0; i<3; i++) {
			new Thread(new MyRunnable("Runnable-"+i) ,"Thread-"+i).start();		
		}
			
		//test new thread from the same MyRunnable, so ticket is shared. 
		//note: it is not thread safe since multiple threads modify the ticket.
		Runnable target = new MyRunnable("Runnable-");
		for(int i=0; i<3; i++) {
			new Thread(target,"Thread-"+i).start();
		}
		
	}

}
