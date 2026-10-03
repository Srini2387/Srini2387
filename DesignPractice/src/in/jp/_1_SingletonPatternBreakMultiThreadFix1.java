package in.jp;

final class Singleton21 {

	private static volatile Singleton21 _instance;
	
	private Singleton21() {}
	
	//synchronized
	public static synchronized Singleton21 getInstance() {
		if(null == _instance) {
			_instance = new Singleton21();
		}
		return _instance;
	}

}

public class _1_SingletonPatternBreakMultiThreadFix1 {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			Singleton21 inst =Singleton21.getInstance();
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