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
- Add the jars in  ``build/install/tutorialj/lib/`` to your classpath, OR, add
the following to your project gradle script 
```
dependencies {
  compile group: 'com.3rdf', name: 'tutorialj', version: '1.0'
{
repositories {
  mavenCentral()
  jcenter()
  maven {
    url "http://www.stat.ubc.ca/~bouchard/maven/"
  }
  maven {
    url "http://spoon.gforge.inria.fr/repositories/releases/"
  }
}
```




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
<sub>From:[tutorialj.Example](src/main/java//tutorialj/Example.java)</sub>

Use 
```
java -cp [all your dependencies and tutorialj's] tutorialj.Main [src]
``` 
to output to standard out the tutorial in markdown,
where ``[src]`` is the root src folder (if more than one, separate by :). For example, this page was 
generated using
```bash
java -cp build/install/tutorialj/lib/\* tutorialj.Main src/main/java/:src/test/java/ > README.md 
```
ran from the repository root. Note that the spoon parser used by this 
library may output spurious warning messages without affecting the 
process. If, however, dependencies are not in the classpath (for example,
junit in the compile unit), some nodes might be skipped. To avoid this,
make sure all the depencies of the program you are generating a tutorial
for are in the classpath.



API
---


See:[tutorialj](src/main/java//tutorialj.java)

