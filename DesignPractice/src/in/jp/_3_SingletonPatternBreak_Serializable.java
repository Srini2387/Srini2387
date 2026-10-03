package in.jp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

final class Singleton5 implements Serializable{

    private static volatile Singleton5 instance;

    private Singleton5() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance()");
        }
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}


public class _3_SingletonPatternBreak_Serializable {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
	Singleton5 s1 = Singleton5.getInstance();

    // Serialize
    ObjectOutputStream out =
            new ObjectOutputStream(
                    new FileOutputStream("Singleton5.ser"));

    out.writeObject(s1);
    out.close();

    // Deserialize
    ObjectInputStream in =
            new ObjectInputStream(
                    new FileInputStream("Singleton5.ser"));

    Singleton5 s2 = (Singleton5) in.readObject();
    in.close();

    System.out.println(s1);
    System.out.println(s2);

    System.out.println(s1 == s2);}
	
	
}