package in.jp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

final class Singleton4 {

    private static volatile Singleton4 instance;

    //guard the constructor
    private Singleton4() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance()");
        }
    }

    public static Singleton4 getInstance() {
    	if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}


public class _2_SingletonPatternBreak_ReflectionFix {
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { 
		Constructor<Singleton4> constructor =
				Singleton4.class.getDeclaredConstructor();

		constructor.setAccessible(true);

		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = constructor.newInstance();
		System.out.println("s1 instance = "+s1.hashCode());
		System.out.println("s2 instance = "+s2.hashCode());

		System.out.println(s1 == s2); // false

		
	}
	
	
}