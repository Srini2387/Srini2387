package pattern.matching;

public class PatternMatching {

	public static void main(String[] args) {
		Object obj = "Hello";

		//before
		if (obj instanceof String) {
		    String str = (String) obj;
		    System.out.println(str.length());
		}
		
		//after
		if (obj instanceof String str) {
		    System.out.println(str.length());
		}


	}

}
