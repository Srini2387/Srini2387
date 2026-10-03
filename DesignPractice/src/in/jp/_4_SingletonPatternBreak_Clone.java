package in.jp;

import java.io.FileNotFoundException;
import java.io.IOException;

final class Singleton61 implements Cloneable {
	
	private static volatile Singleton61 instance;

	private Singleton61() {
	}

	public static Singleton61 getInstance() {
		if (instance == null) {
			synchronized (Singleton61.class) {
				if (instance == null) {
					instance = new Singleton61();
				}
			}
		}
		return instance;
	}

	@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


public class _4_SingletonPatternBreak_Clone {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException{
		
	Singleton61 s1 = Singleton61.getInstance();

    Singleton61 s3 = (Singleton61) s1.clone();
    
    System.out.println(s1.hashCode());
    System.out.println(s3.hashCode());

    System.out.println(s1 == s3);}
	
	
}