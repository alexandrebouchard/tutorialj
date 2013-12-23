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
 * java code repositories, it is less useful for introducing a new user to a 
 * code base.
 * 
 * The goal of tutorialj is to provide guided tours of code repository through
 * tutorials generated from annotated javadoc-style comments. 
 * 
 * Installation
 * ------------
 * 
 * TODO
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
   * The argument order controls the order of tutorial nodes. The argument 
   * ``showSource=true`` makes the code below the annotation 
   * appears. This is rendered as shown below.
   */
  @Tutorial(order=1,showSource=true)
  public static void example()
  {
    System.out.println("Hello, world!");
  }
}
