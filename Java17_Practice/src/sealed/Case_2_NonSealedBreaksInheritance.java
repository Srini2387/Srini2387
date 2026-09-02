package sealed;

public class Case_2_NonSealedBreaksInheritance {
//non-sealed breaks the restriction
}

sealed class Animal2 permits Dog2, Cat2 {
}

final class Dog2 extends Animal2 {
}

non-sealed class Cat2 extends Animal2 {
}

class Tiger2 extends Cat2 {   // Valid
}
