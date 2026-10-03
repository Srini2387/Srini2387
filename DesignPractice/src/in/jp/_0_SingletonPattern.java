package in.jp;

final class Singleton {

	private static volatile Singleton _instance;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(null == _instance) {
			_instance = new Singleton();
		}
		return _instance;
	}

}

public class _0_SingletonPattern {
	public static void main(String[] args) {
		System.out.println(Singleton.getInstance().hashCode());
		System.out.println(Singleton.getInstance().hashCode());
		System.out.println(Singleton.getInstance().hashCode());
	}
}