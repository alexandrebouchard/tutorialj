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
 * There are two ways to install:
 * 
 * ### Integrate to a gradle script
 * 
 * Simply add the following lines (replacing 1.3.10 by the current version):
 * 
 * ```groovy
 * repositories {
 *  maven {
 *     url "http://www.stat.ubc.ca/~bouchard/maven/"
 *   }
 * }
 * 
 * dependencies {
 *   compile group: 'com.3rdf', name: 'tutorialj', version: '1.3.10'
 * }
 * 
 * task(tutorialj, dependsOn: ['build','testClasses'], type: JavaExec) {
 *   main = 'tutorialj.Main'
 *   classpath {
 *     configurations.compile
 *     sourceSets.main.runtimeClasspath
 *     sourceSets.test.runtimeClasspath
 *   }
 * }
 * ```
 * 
 * ### Compile using the provided gradle script
 * 
 * - Check out the source ``git clone git@github.com:alexandrebouchard/tutorialj.git``
 * - Compile using ``gradle installApp``
 * - Add the jars in  ``build/install/tutorialj/lib/`` to your classpath
 * - Add ``build/install/tutorialj/bin/`` to your path
 * 
 * Usage
 * -----
 * 
 * ### Creating a basic tutorial
 * 
 * Tutorials are just sequences of nodes. Each node is a javadoc comment, with its
 * contents rendered as markdown, and optionally, source code and/or link to javadoc/full
 * code browser.
 * 
 * To create the first step/node of a tutorial, add the following tag above a 
 * method, class, field, or constructor:
 * ```java
 * @Tutorial(startTutorial = "README.md")
 * ```  
 * The ``startTutorial`` argument should currently be used only in the first tag, 
 * and specifies the name of the file where the tutorial will be written to.
 * 
 * This will display the contents of the javadoc block of comments above
 * the tag.
 */
@Tutorial(startTutorial = "README.md")
public class Example
{
  
  /**
   * To display the contents of other javadoc blocks in the same source file, use
   * ```java
   * @Tutorial
   * ```
   * above each desired method, field or constructor.
   * 
   * The nodes will be displayed in the same order as they appear in the file.
   * 
   * Code snippets can also be displayed. This is done using 
   * ```java
   * @Tutorial(showSource=true)
   * ```
   * This is rendered as shown below. Note that object
   * references are rewritten to show the fully qualified names, to make it
   * easier to copy and paste code.
   */
  @Tutorial(showSource=true)
  public static void example()
  {
    System.out.println("Hello, world!");
  }
  
  /**
   * ### Generating the tutorial
   * 
   * To generate the tutorial using the gradle method, simply type ``gradle tutorialj``.
   * To do it using the command line application, use:
   * ```
   * java -cp [all your dependencies and tutorialj's] tutorialj.Main --sourceFiles [src]
   * ``` 
   * to output to standard out the tutorial in markdown,
   * where ``[src]`` is the root src folder (if more than one, separate by ``:``). For example, this page was 
   * generated using
   * ```bash
   * java -cp build/install/tutorialj/lib/\* tutorialj.Main --sourceFiles src/main/java/:src/test/java/
   * ```
   * ran from the repository root. 
   * 
   * ### Misc. other features
   * 
   * To create tutorials that span several classes, use:
   * ```
   * @Tutorial(nextStep = [name of class].class)
   * ```
   * for example:
   * ```
   * @Tutorial(nextStep = Tutorial.class)
   * ```
   */
  @Tutorial(nextStep = GenerateTutorials.class)
  public static void example2() 
  {
  }
  
  /**
   * Links to an html javadoc/full code browser can be 
   * created via the ``showLink = true`` argument. See API below 
   * for options.
   */
  @Tutorial(nextStep = Tutorial.class, showLink = true, linkTextPrefix = "Full source of this tutorial: ", linkTextSuffix = "")
  public static void example3()
  {
    
  }
}
