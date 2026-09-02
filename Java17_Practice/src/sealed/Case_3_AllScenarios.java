package sealed;

public class Case_3_AllScenarios {
//A permitted class cannot forget to declare its sealing status
}

sealed class Animal permits Dog {
}

//class Dog extends Animal {   // ❌ Compile-time error
//}

//either one below is fine

//scenario-1
//final class Dog extends Animal {}

//scenario-2
//sealed class Dog extends Animal permits Puppy {}
//final class Puppy extends Dog {}

//scenario-3
non-sealed class Dog extends Animal {}
final class Puppy extends Dog {}
