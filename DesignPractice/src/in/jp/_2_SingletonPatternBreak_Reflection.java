package in.jp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

final class Singleton3 {

	private static Singleton3 _instance;
	
	private Singleton3() {}
	
	public static Singleton3 getInstance() {
		if(null == _instance) {
			_instance = new Singleton3();
		}
		return _instance;
	}

}

public class _2_SingletonPatternBreak_Reflection {
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		Constructor<Singleton3> constructor =
				Singleton3.class.getDeclaredConstructor();

		constructor.setAccessible(true);

		Singleton3 s1 = Singleton3.getInstance();
		Singleton3 s2 = constructor.newInstance();
		System.out.println("s1 instance = "+s1.hashCode());
		System.out.println("s2 instance = "+s2.hashCode());

		System.out.println(s1 == s2); // false

		
	}
	
	
}