Java Singleton Pattern
What is Singleton?

The Singleton Design Pattern ensures that a class has only one instance and provides a global access point to that instance.

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


Problem: This implementation is not thread-safe.

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

The two checks are intentional:

if (instance == null) {
    synchronized (Singleton.class) {
        if (instance == null) {
            instance = new Singleton();
        }
    }
}


The first check avoids synchronization after the Singleton has already been created.

The second check prevents two threads waiting on the lock from creating two instances.

3. Simple Thread-Safe Singleton

If lazy initialization isn't required:

public final class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}


The JVM guarantees safe class initialization, making this thread-safe.

This is eager initialization because the instance is created when the class is initialized.

4. Initialization-on-Demand Holder Idiom

A clean approach for lazy + thread-safe initialization:

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

For a non-enum Singleton, the Holder Idiom is a clean and commonly recommended approach.

5. Break Singleton Using Reflection

Reflection can access the private constructor:

Constructor<Singleton> constructor =
        Singleton.class.getDeclaredConstructor();

constructor.setAccessible(true);

Singleton s1 = Singleton.getInstance();
Singleton s2 = constructor.newInstance();

System.out.println(s1 == s2); // false


The private constructor:

private Singleton() {
}


doesn't completely protect against reflective access.

Basic Protection
private Singleton() {

    if (instance != null) {
        throw new RuntimeException("Singleton already exists");
    }
}


Reflection can bypass normal access control and invoke a private constructor.

For stronger reflection resistance, an enum Singleton is generally preferable when suitable.

6. Break Singleton Using Serialization

If Singleton implements Serializable:

public final class Singleton implements Serializable {
    // ...
}


Serialization and deserialization can produce another instance.

Singleton s1 = Singleton.getInstance();

// serialize s1
// deserialize

Singleton s2 = (Singleton) input.readObject();

System.out.println(s1 == s2); // false

Why?

Deserialization can reconstruct an object without using the normal Singleton creation path.

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


readResolve() ensures that deserialization returns the existing Singleton instance instead of a separate instance.

7. Break Singleton Using clone()

If Singleton implements Cloneable and allows cloning:

public final class Singleton implements Cloneable {

    @Override
    public Singleton clone() throws CloneNotSupportedException {
        return (Singleton) super.clone();
    }
}


Then:

Singleton s1 = Singleton.getInstance();
Singleton s2 = s1.clone();

System.out.println(s1 == s2); // false

Why?

Object.clone() creates another object without invoking the Singleton constructor.

Therefore:

Singleton instance
       │
       │ clone()
       ↓
New Singleton object

Fix

Don't implement Cloneable.

Or explicitly prevent cloning:

@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}


Prevent cloning because clone() can create a second instance without calling the private constructor.

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


Singleton uniqueness is generally scoped to the ClassLoader that loaded the class.

This is mainly relevant in:

Application servers

Plugin systems

Modular applications

Complex runtime environments

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

Why Enum Singleton is Special

Enum instances are managed by the JVM.

Common Singleton attacks are handled by Java's enum semantics:

                    Normal Singleton       Enum Singleton

Multithreading      Needs protection       JVM handles initialization
Reflection          Can break              Protected
Serialization       Can break              Protected
Cloning              Can break              Protected


Enum Singleton is the strongest built-in Java mechanism for Singleton semantics when an enum is appropriate.

10. Enum + Serialization

Enum Singleton does not need readResolve().

public enum Singleton {
    INSTANCE;
}


After serialization and deserialization:

Singleton s1 = Singleton.INSTANCE;

// serialize
// deserialize

Singleton s2 = (Singleton) input.readObject();

System.out.println(s1 == s2); // true


Enum serialization preserves the enum constant identity.

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


The JVM explicitly prevents reflective creation of enum objects.

12. Enum + Cloning

Enum constants cannot be cloned.

Enum.clone() is protected and final and throws:

CloneNotSupportedException


Therefore, cloning cannot be used to create another enum instance.

Enum constants cannot be cloned into another enum instance.

13. Singleton Attack Summary
Technique	How Singleton is Broken	Typical Fix
Multithreading	Two threads create instances	synchronized / DCL
Reflection	Private constructor accessed	Constructor guard
Serialization	Deserialization creates another instance	readResolve()
Cloning	clone() creates another object	Don't implement Cloneable
Multiple ClassLoaders	Separate class instances per loader	ClassLoader/architecture control
14. Recommended Approaches
Non-Enum Singleton

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


It provides lazy initialization and thread safety without explicit synchronization.

Enum Singleton

When an enum is appropriate:

public enum Singleton {
    INSTANCE;
}


Enum is the simplest and most robust built-in Java approach for Singleton semantics.

15. Interview Cheat Sheet
How do you make Singleton thread-safe?

Use synchronized initialization, double-checked locking with volatile, static initialization, or the Holder idiom.

How can reflection break Singleton?

Reflection can access the private constructor and create another instance.

How can serialization break Singleton?

Deserialization can create another instance. Use readResolve().

How can cloning break Singleton?

clone() can create another object without invoking the private constructor. Don't implement Cloneable or explicitly prevent cloning.

What is a clean non-enum Singleton?

Initialization-on-Demand Holder Idiom.

What is the simplest robust Java Singleton?

An enum Singleton, when an enum is suitable for the use case.

Final Takeaway
                     Singleton
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   Creation          Breaking          Protection
        │                │                │
        │          ┌─────┼─────┐          │
        │          │     │     │          │
    getInstance()  Thread Reflection Serialization
                   │     │     │
                   │     │     └── readResolve()
                   │     └──────── Constructor guard
                   └────────────── synchronized / DCL
                         
        Clone → Don't implement Cloneable
        ClassLoader → Uniqueness is ClassLoader-scoped

        Enum Singleton
             ↓
        JVM-managed instance
             ↓
     Strong Singleton semantics

⭐ Most Important Points

private constructor prevents normal object creation, but reflection can bypass it.

volatile + double-checked locking provides thread-safe lazy initialization.

readResolve() protects a normal Serializable Singleton from deserialization.

Don't implement Cloneable if you want to prevent cloning.

Singleton uniqueness is generally scoped to a ClassLoader.

The Holder Idiom is a clean choice for a non-enum Singleton.

Enum Singleton avoids the common reflection, serialization, and cloning problems of traditional Singleton implementations.
