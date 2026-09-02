package records;

public final class _1_BeforeRecordPerson {
    private final String name;
    private final int age;

    public _1_BeforeRecordPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    @Override
    public boolean equals(Object o) { return false; }

    @Override
    public int hashCode() { return 0; }

    @Override
    public String toString() { return "asdf"; }
}
