package in.jp;

final class Singleton22 {

	private static volatile Singleton22 _instance;
	
	private Singleton22() {}
	
	//double locked
	public static Singleton22 getInstance() {
        if (_instance == null) {
            synchronized (Singleton22.class) {
                if (_instance == null) {
                	_instance = new Singleton22();
                }
            }
        }
        return _instance;
    }

}

public class _1_SingletonPatternBreakMultiThreadFix2 {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			Singleton22 inst =Singleton22.getInstance();
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