package tutorialj;


/**
 * Summary
 * -------
 * 
 * tutorialj is an annotation-based utility to create tutorials from java code.
 * 
 * Goal
 * ----
 * 
 * While javadoc provides an effective way of building encyclopedic access to 
 * java code repositories, it is less adapted to the task of introducing a new user to a 
 * code base.
 * 
 * The goal of tutorialj is to help creating guided code tours via
 * java annotation and markdown generation. 
 * 
 * Installation
 * ------------
 * 
 * - Compile using ``gradle installApp``
 * - Add the generated folder ``build/install/tutorialj/bin`` into your classpath
 * - Add the jars in  ``build/install/tutorialj/lib/`` to your classpath, OR, add
 * the following to your project gradle script 
 * ```compile group: 'com.3rdf', name: 'tutorialj', version: '1.0'```
 * 
 */
@Tutorial(order=0)
public class Example
{
  
  /**
   * Usage
   * -----
   * 
   * To add a step to a tutorial to your code, add the following tag above a 
   * method, class, field, or constructor:
   * ```java
   * @Tutorial(order=1,showSource=true)
   * ```
   * 
   * The argument ``order`` controls the order of tutorial nodes. The argument 
   * ``showSource=true`` makes the code below the annotation 
   * appears. This is rendered as shown below.
   */
  @Tutorial(order=1,showSource=true)
  public static void example()
  {
    System.out.println("Hello, world!");
  }
  
  /**
   * Use ``tutorialj [src]`` to output to standard out the tutorial in markdown,
   * where ``[src]`` is the root src folder. For example, this page was 
   * generated using
   * ```bash
   * tutorialj src/main/java/ > README.md 
   * ```
   * ran from the repository root.
   * 
   * See ``tutorialj.Example`` for the generating source.
   */
  @Tutorial(order=2)
  public static void example2() {}
}
