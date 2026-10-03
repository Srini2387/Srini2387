package in.jp;

import java.io.FileNotFoundException;
import java.io.IOException;

final class Singleton62 implements Cloneable {
	
	private static volatile Singleton62 instance;

	private Singleton62() {
	}

	public static Singleton62 getInstance() {
		if (instance == null) {
			synchronized (Singleton62.class) {
				if (instance == null) {
					instance = new Singleton62();
				}
			}
		}
		return instance;
	}

	@Override
    protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
    }
}


public class _4_SingletonPatternBreak_CloneFix {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException{
		
	Singleton62 s1 = Singleton62.getInstance();

    Singleton62 s3 = (Singleton62) s1.clone();
    
    System.out.println(s1.hashCode());
    System.out.println(s3.hashCode());

    System.out.println(s1 == s3);}
	
	
}