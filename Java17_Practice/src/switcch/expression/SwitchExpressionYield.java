package switcch.expression;

public class SwitchExpressionYield {
public static void main(String[] args) {
	int day = 3;
	String result = switch (day) {
    case 1 -> {
        System.out.println("Monday");
        yield "Weekday";
    }
    default -> "Other";
};
System.out.println(result);
}
}
