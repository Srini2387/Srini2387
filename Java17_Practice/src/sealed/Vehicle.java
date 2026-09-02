package sealed;

sealed class Vehicle permits Car, Bike {
	
}

final class Car extends Vehicle {
	
}

final class Bike extends Vehicle {
	
}