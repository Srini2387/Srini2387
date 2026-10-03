Java Singleton Pattern
What is Singleton?

The Singleton Design Pattern ensures that a class has only one instance and provides a global access point to it.

A typical Singleton has:

A private constructor

A static instance

A public static getInstance() method

1. Basic Singleton
public final class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


Usage:

Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();

System.out.println(s1 == s2); // true


The problem: this implementation is not thread-safe.

2. Break Singleton Using Multithreading

Two threads can simultaneously see:

instance == null


and both create an object.

Thread 1                    Thread 2

instance == null            instance == null
       ↓                           ↓
new Singleton()              new Singleton()
       ↓                           ↓
     Object 1                   Object 2


Result:

s1 != s2

Fix: Double-Checked Locking
public final class Singleton {

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {

                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}

Important

volatile is required for the double-checked locking implementation.

3. Simpler Thread-Safe Singleton

If lazy initialization isn't required:

public final class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}


The JVM guarantees safe class initialization.

4. Initialization-on-Demand Holder Idiom

A clean lazy and thread-safe approach:

public final class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}

Advantages

Thread-safe

Lazy initialization

No explicit synchronization

No volatile

Simple implementation

This is a strong choice for a non-enum Singleton.

5. Break Singleton Using Reflection

Reflection can access the private constructor:

Constructor<Singleton> constructor =
        Singleton.class.getDeclaredConstructor();

constructor.setAccessible(true);

Singleton s1 = Singleton.getInstance();
Singleton s2 = constructor.newInstance();

System.out.println(s1 == s2); // false


The private constructor does not protect against reflective access.

Basic Protection
private Singleton() {

    if (instance != null) {
        throw new RuntimeException("Singleton already exists");
    }
}


However, a normal class requires additional care if you want strong protection against reflection.

6. Break Singleton Using Serialization

If Singleton implements Serializable:

public final class Singleton implements Serializable {
    // ...
}


Serialization/deserialization can produce another instance.

Singleton s1 = Singleton.getInstance();

// serialize s1
// deserialize

Singleton s2 = (Singleton) input.readObject();

System.out.println(s1 == s2); // false

Fix: readResolve()
private Object readResolve() {
    return getInstance();
}


Complete pattern:

public final class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private Object readResolve() {
        return getInstance();
    }
}

Key interview point

readResolve() prevents deserialization from returning a separate Singleton instance.

7. Break Singleton Using clone()

If Singleton implements Cloneable and allows cloning:

public final class Singleton implements Cloneable {

    // ...

    @Override
    public Singleton clone() throws CloneNotSupportedException {
        return (Singleton) super.clone();
    }
}


Then:

Singleton s1 = Singleton.getInstance();
Singleton s2 = s1.clone();

System.out.println(s1 == s2); // false


Object.clone() creates another object without calling the Singleton constructor.

Fix

Don't implement Cloneable.

Or explicitly prevent cloning:

@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}

8. Multiple ClassLoaders

A Singleton is normally unique within a particular ClassLoader.

Different ClassLoaders can load the same Singleton class separately:

ClassLoader A
      ↓
 Singleton.class
      ↓
 Instance A


ClassLoader B
      ↓
 Singleton.class
      ↓
 Instance B


Therefore:

InstanceA != InstanceB


This is mainly relevant in application servers, plugin systems, and modular environments.

9. Enum Singleton

Java provides a very robust Singleton mechanism through enum.

public enum Singleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Hello");
    }
}


Usage:

Singleton s1 = Singleton.INSTANCE;
Singleton s2 = Singleton.INSTANCE;

System.out.println(s1 == s2); // true

Why Enum Singleton is special

Enum instances are managed by the JVM.

Common Singleton attacks are handled by Java's enum semantics:

                    Normal Singleton       Enum Singleton

Multithreading      Needs protection       JVM handles initialization
Reflection          Can break              Protected
Serialization       Can break              Protected
Cloning              Can break              Protected

10. Enum + Serialization

Enum Singleton does not need readResolve().

public enum Singleton {
    INSTANCE;
}


Serialization and deserialization preserve the enum constant:

Singleton s1 = Singleton.INSTANCE;

// serialize
// deserialize

Singleton s2 = (Singleton) input.readObject();

System.out.println(s1 == s2); // true


Unlike a normal Serializable Singleton, deserialization does not create another enum instance.

11. Enum + Reflection

Normal reflection cannot create another enum instance.

Attempting:

Constructor<?> constructor =
        Singleton.class.getDeclaredConstructors()[0];

constructor.setAccessible(true);

constructor.newInstance("HACKED", 0);


results in an exception such as:

IllegalArgumentException:
Cannot reflectively create enum objects


The JVM explicitly prevents reflective construction of enum objects.

12. Enum + Cloning

Enum constants cannot be cloned.

Enum.clone() is protected and final and throws:

CloneNotSupportedException


Therefore:

Singleton.INSTANCE.clone();


cannot be used to create another enum instance.

13. Singleton Attack Summary
Technique	How Singleton is broken	Typical Fix
Multithreading	Two threads create instances	synchronized / DCL
Reflection	Private constructor accessed	Constructor guard
Serialization	Deserialization creates another instance	readResolve()
Cloning	clone() creates another object	Don't implement Cloneable
Multiple ClassLoaders	Separate class instances per loader	ClassLoader/architecture control
14. Recommended Approaches
Non-enum Singleton

Use the Initialization-on-Demand Holder Idiom:

public final class Singleton {

    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}

Enum Singleton

When an enum is appropriate:

public enum Singleton {
    INSTANCE;
}


The enum approach provides strong protection against the common ways of breaking a traditional Singleton.

Interview Takeaways

Remember these points:

Singleton
   │
   ├── Multithreading
   │      → synchronized / volatile + double-check
   │
   ├── Reflection
   │      → private constructor can be bypassed
   │      → constructor guard
   │
   ├── Serialization
   │      → readResolve()
   │
   ├── Cloning
   │      → don't implement Cloneable
   │      → or throw CloneNotSupportedException
   │
   └── Multiple ClassLoaders
          → each ClassLoader can have its own instance

Most important interview answers

Q: How do you make Singleton thread-safe?

Use synchronized initialization, double-checked locking with volatile, static initialization, or the Holder idiom.

Q: How can reflection break Singleton?

Reflection can access the private constructor and create another instance.

Q: How can serialization break Singleton?

Deserialization can create another instance. Use readResolve().

Q: How can cloning break Singleton?

clone() can create another object without invoking the private constructor. Don't implement Cloneable or explicitly prevent cloning.

Q: What's a clean non-enum Singleton?

Initialization-on-Demand Holder Idiom.

Q: What's the most robust built-in Java Singleton mechanism?

enum, when an enum is suitable for the use case.
