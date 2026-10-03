package in.jp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class _6_EnumSingletonBreak {

	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ClassNotFoundException { 
		
		
		
		//Multi thread Break
//		Runnable task = () -> {
//			_6_EnumSingleton inst =_6_EnumSingleton.INSTANCE;
//			System.out.println(Thread.currentThread().getName()+" : "+ inst.hashCode());
//		};
//		
//		Thread t1 = new Thread(task, "Thread-1"); 
//		Thread t2 = new Thread(task, "Thread-2");
//		
//		t1.start();
//		t2.start();
//		
//		t1.join();
//		t2.join();
		
		
		//Reflection Break
		Constructor<?> constructor =
				_6_EnumSingleton.class.getDeclaredConstructors()[0];

		constructor.setAccessible(true);

//		_6_EnumSingleton s1 =
//		        (_6_EnumSingleton) constructor.newInstance("HACKED", 0); //java.lang.IllegalArgumentException: Cannot reflectively create enum objects
//		
//		_6_EnumSingleton s2 =
//				(_6_EnumSingleton) constructor.newInstance("HACKED", 0);
//
//
//		System.out.println("s1 instance = "+s1.hashCode());
//		System.out.println("s2 instance = "+s2.hashCode());
//
//		System.out.println(s1 == s2); // false
		
		//Serializable Break
//		_6_EnumSingleton s1 = _6_EnumSingleton.INSTANCE;
//
//	    ObjectOutputStream out =
//	            new ObjectOutputStream(
//	                    new FileOutputStream("_6_EnumSingleton.ser"));
//
//	    out.writeObject(s1);
//	    out.close();
//
//	    ObjectInputStream in =
//	            new ObjectInputStream(
//	                    new FileInputStream("_6_EnumSingleton.ser"));
//
//	    _6_EnumSingleton s2 = (_6_EnumSingleton) in.readObject();
//	    in.close();
//
//	    System.out.println(s1.hashCode());
//	    System.out.println(s2.hashCode());
//
//	    System.out.println(s1 == s2);
		
		//clone
		//_6_EnumSingleton s1 = _6_EnumSingleton.INSTANCE.clone();//won't compile

		
	}

}






