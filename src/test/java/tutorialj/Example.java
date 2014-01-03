package tutorialj;

public class Example
{
  
  /**
   * 
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
   * The goal of tutorialj is to help creating guided code tours. This is achieved using 
   * java annotations. Since the source of the tutorials are in java, their syntactic
   * correctness is easier to enforce, and to maintain when refactoring. By combining 
   * tutorialj with JUnit annotation, it is also easy to check that the usage examples
   * also behave as expected.
   * 
   * We do not attempt to be have interoperability or to supersede javadoc. We 
   * expect that the methods annotated with this tool will be disjoint from those
   * annotated with javadoc: javadoc targets public api method, while we target usage
   * examples, which will often be located in src/test and/or private. 
   * 
   * Installation
   * ------------
   * 
   * There are two ways to install:
   * 
   * ### Integrate to a gradle script
   * 
   * Simply add the following lines (replacing 2.0.0 by the current version):
   * 
   * ```groovy
   * repositories {
   *  mavenCentral()
   *  jcenter()
   *  maven {
   *     url "http://www.stat.ubc.ca/~bouchard/maven/"
   *   }
   * }
   * 
   * dependencies {
   *   compile group: 'com.3rdf', name: 'tutorialj', version: '2.0.0'
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
   * Tutorials are just sequences of nodes. Each node is a javadoc comment, and 
   * optionally, source code and/or link to javadoc/full
   * code browser.
   * 
   * To create the first step/node of a tutorial, add the following tag above a 
   * method or static method:
   * ```java
   * @Tutorial(startTutorial = "README.md")
   * ```  
   * The ``startTutorial`` specifies the name of the file where the tutorial will be written to.
   * Currently we support only one such node per project.
   * 
   * This will display the contents of the javadoc block of comments above
   * the tag.
   * To display the contents of other javadoc blocks in the same source file, use
   * ```java
   * @Tutorial
   * ```
   * above each desired method. 
   * 
   * The nodes will be displayed in the same order as they appear in the file.
   * 
   * The code for the annotated function is displayed by default. To skip it, use 
   * ```java
   * @Tutorial(showSource=false)
   * ```
   * This is rendered as shown below. 
   */
  @Tutorial(startTutorial = "README.md")
  public static void example() 
  {
    System.out.println("Hello, world!");
  }
  
  /**
   * To show the signature as well, use 
   * ```java
   * @Tutorial(showSignature = true)
   * ```
   */
  @Tutorial(showSignature = true)
  public static void methodWithSignature() throws Exception
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
  @Tutorial(showSource = false, nextStep = GenerateTutorials.class)
  public static void example2() 
  {
  }
  
  /**
   * Links to an html javadoc/full code browser can be 
   * created via the ``showLink = true`` argument. 
   */
  @Tutorial(showSource = false, showLink = true, linkTextPrefix = "Full source of this tutorial: ", linkTextSuffix = "", linkPrefix = "src/test/java/")
  public static void example3()
  {
    
  }
}
