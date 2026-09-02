package sealed;

public class Case_1_IndirectInheritance {
//Indirect inheritance
//If Animal permits Mammal, it doesn't need to explicitly permit Dog:
}

sealed class Animal1 permits Mammal1 {
}

sealed class Mammal1 extends Animal1 permits Dog1 {
}

final class Dog1 extends Mammal1 {
}
