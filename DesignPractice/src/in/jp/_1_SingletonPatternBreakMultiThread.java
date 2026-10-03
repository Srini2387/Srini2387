package in.jp;

final class Singleton2 {

	private static volatile Singleton2 _instance;
	
	private Singleton2() {}
	
	public static Singleton2 getInstance() {
		if(null == _instance) {
			_instance = new Singleton2();
		}
		return _instance;
	}

}

public class _1_SingletonPatternBreakMultiThread {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			Singleton2 inst =Singleton2.getInstance();
			System.out.println(Thread.currentThread().getName()+" : "+ inst.hashCode());
		};
		
		Thread t1 = new Thread(task, "Thread-1"); 
		Thread t2 = new Thread(task, "Thread-2");
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	
}