Summary
-------

tutorialj is an annotation-based utility to create tutorials from java code.

Goal
----

While javadoc provides an effective way of building encyclopedic access to 
java code repositories, it is less adapted to the task of introducing a new user to a 
code base.

The goal of tutorialj is to help creating guided code tours via
java annotation and markdown generation. 

Installation
------------

- Compile using ``gradle installApp``
- Add the generated folder ``build/install/tutorialj/bin`` into your classpath
- Add the jars in  ``build/install/tutorialj/lib/`` to your classpath 
(TODO: gradle/maven integration)




Usage
-----

To add a step to a tutorial to your code, add the following tag above a 
method, class, field, or constructor:
```java
@Tutorial(order=1,showSource=true)
```

The argument ``order`` controls the order of tutorial nodes. The argument 
``showSource=true`` makes the code below the annotation 
appears. This is rendered as shown below.


```java

public static void example() {
    java.lang.System.out.println("Hello, world!");
}
```

Use ``tutorialj [src]`` to output to standard out the tutorial in markdown,
where ``[src]`` is the root src folder. For example, this page was 
generated using
```bash
tutorialj src/main/java/ > README.md 
```
ran from the repository root.

See ``tutorialj.Example`` for the generating source.



API
---


```java

@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.SOURCE)
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.CONSTRUCTOR ,java.lang.annotation.ElementType.FIELD ,java.lang.annotation.ElementType.METHOD ,java.lang.annotation.ElementType.TYPE})
public @interface Tutorial {
    boolean showSource() default false;
    
    double order() default 0.0;
    
}
```

