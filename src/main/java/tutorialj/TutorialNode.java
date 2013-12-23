package tutorialj;

import java.util.Random;

import spoon.reflect.declaration.CtElement;

import com.google.common.base.Splitter;



public class TutorialNode implements Comparable<TutorialNode>
{
  private final Tutorial annotation;
  private final CtElement element;
  private final double priority;
  
  public TutorialNode(Tutorial annotation, CtElement element)
  {
    this.annotation = annotation;
    this.element = element;
    this.priority = priority();
  }

  private double priority()
  {
    // induces a unique, deterministic order
    double jitter = new Random(element.getSignature().hashCode()).nextDouble() / 10.0;
    return jitter + annotation.order();
  }

  @Override
  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append("Signature:" + element.getSignature() + "\n");
    String comments = element.getDocComment();
    if (comments != null)
      result.append(removeFirstSpaceOfEachLine(comments) + "\n");
    if (annotation.showSource())
      result.append("```java\n" + codeWithoutComments(element) + "\n```\n");
    return result.toString();
  }

  private static String removeFirstSpaceOfEachLine(String comments)
  {
    StringBuilder result = new StringBuilder();
    for (String line : Splitter.on("\n").split(comments))
    {
      if (!line.isEmpty() && line.charAt(0) == ' ')
        line = line.substring(1);
      result.append(line + "\n");
    }
    return result.toString();
  }

  private static String codeWithoutComments(CtElement element)
  {
    return element.toString()
      .replaceFirst("/\\*\\*(?s:(?!\\*/).)*\\*/", "") // remove javadoc comment
      .replaceFirst("@Tutorial\\s*([(][^)]+[)])?", "") // remove annotation
      ;
  }

  @Override
  public int compareTo(TutorialNode o)
  {
    return Double.compare(this.priority, o.priority);
  }
  
  
}