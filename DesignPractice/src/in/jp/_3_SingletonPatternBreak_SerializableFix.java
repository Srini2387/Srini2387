package in.jp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

final class Singleton6 implements Serializable {
	private static volatile Singleton6 instance;

	private Singleton6() {
	}

	public static Singleton6 getInstance() {
		if (instance == null) {
			synchronized (Singleton6.class) {
				if (instance == null) {
					instance = new Singleton6();
				}
			}
		}
		return instance;
	}

	//fix
	private Object readResolve() {
		return getInstance();
	}
}


public class _3_SingletonPatternBreak_SerializableFix {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		Singleton6 s1 = Singleton6.getInstance();

    // Serialize
    ObjectOutputStream out =
            new ObjectOutputStream(
                    new FileOutputStream("Singleton6.ser"));

    out.writeObject(s1);
    out.close();

    // Deserialize
    ObjectInputStream in =
            new ObjectInputStream(
                    new FileInputStream("Singleton6.ser"));

    Singleton6 s2 = (Singleton6) in.readObject();
    in.close();

    System.out.println(s1);
    System.out.println(s2);

    System.out.println(s1 == s2);}
	
	
}