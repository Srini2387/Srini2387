package sealed;

public class Case_5_Records {
//Records work naturally with sealed classes
	//Because records are implicitly final:
}

sealed interface Shape5 permits Circle5, Rectangle5 {
}

record Circle5(double radius) implements Shape5 {
}

record Rectangle5(double width, double height) implements Shape5 {
}
