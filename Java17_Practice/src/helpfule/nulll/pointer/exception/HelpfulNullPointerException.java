package helpfule.nulll.pointer.exception;

public class HelpfulNullPointerException {
	
	public static void main(String[] args) {

        User user = new User();

        String city = user.getAddress().getCity().toUpperCase();
        //Exception in thread "main" java.lang.NullPointerException: 
        //Cannot invoke "String.toUpperCase()" because 
        //the return value of "helpfule.nulll.pointer.exception.Address.getCity()" is null

        System.out.println(city);
    }
}

class Address {
    String getCity() {
        return null;
    }
}

class User {
    Address getAddress() {
        return new Address();
    }
}
