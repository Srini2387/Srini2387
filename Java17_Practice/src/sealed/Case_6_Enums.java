package sealed;

public class Case_6_Enums {
//Enums can implement sealed interfaces
	//The enum is implicitly final, so it satisfies the sealed hierarchy.
}

sealed interface Status6 permits StatusCode6 {
}

enum StatusCode6 implements Status6 {
    SUCCESS, ERROR
}
