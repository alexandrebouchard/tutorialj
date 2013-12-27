package tutorialj;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import spoon.reflect.declaration.CtElement;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;



public class TutorialNode //implements Comparable<TutorialNode>
{
  private final Tutorial annotation;
  private final CtElement element;
//  private final double priority;
  
  public TutorialNode(Tutorial annotation, CtElement element)
  {
    this.annotation = annotation;
    this.element = element;
//    this.priority = priority();
  }

//  private double priority()
//  {
//    // induces a unique, deterministic order
//    double jitter = new Random(element.getSignature().hashCode()).nextDouble() / 10.0;
//    return jitter + annotation.order();
//  }
  
  public boolean hasJump()
  {
    if (annotation.nextStep() == null) return false;
    if (annotation.nextStep().equals(NoJump.class)) return false;
    return true;
  }
  
  public String getJumpTarget() 
  { 
    return removeFirstPartOfSignature(annotation.nextStep().toString());
  }
  
  public boolean isTutorialStart()
  {
    if (annotation.startTutorial() == null) return false;
    if (annotation.startTutorial().equals("")) return false;
    return true;
  }

  @Override
  public String toString()
  {
    StringBuilder result = new StringBuilder();
    String comments = element.getDocComment();
    if (comments != null)
      result.append(removeFirstSpaceOfEachLine(comments) + "\n");
    if (annotation.showSource())
      result.append("```java\n" + codeWithoutComments(element) + "\n```\n");
    if (annotation.showLink())
    {
      String className = getEnclosingTypeName();
      result.append(annotation.linkTextPrefix() 
          + "[" + className + "](" + annotation.linkPrefix() + "/" + className.replace('.', '/') 
          + ".java)" + annotation.linkTextSuffix() + "\n");
    }
    return result.toString();
  }

//  public String getTypeName()
//  {
//    return getTypeName(this.element);
//  }
  
  public int getLineNumber()
  {
    return element.getPosition().getLine() - 1;
  }
  
  public String getEnclosingTypeName()
  {
    CtElement element = this.element.getPosition().getCompilationUnit().getMainType();
//    if (!element.getSignature().matches("^(class|interface|[@]interface).*"))
//      element = element.getParent();
    return removeFirstPartOfSignature(element.getSignature());
  }
  
  private static String removeFirstPartOfSignature(String s)
  {
    return s.replaceFirst("(class|interface|[@]interface)\\s+", "");
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
    String initial = 
      
      element.toString()
    
//      element.getPosition().getCompilationUnit().getOriginalSourceCode().substring(element.getParent().getPosition().getSourceStart(), element.getParent().getPosition().getSourceEnd())
    ;
    
//    int startPosition = element.getPosition().getLine() - 1;
//    int length = element.getPosition().getSourceEnd() - element.getPosition().getSourceStart();
//    List<String> rawSource = Arrays.asList(element.getPosition().getCompilationUnit().getOriginalSourceCode().split("\n"));
    return //Joiner.on('\n').join(rawSource.subList(startPosition, startPosition + length))
        initial
      .replaceFirst("/\\*\\*(?s:(?!\\*/).)*\\*/", "") // remove javadoc comment
      .replaceFirst("@Tutorial\\s*([(][^)]+[)])?", "") // remove annotation
      ;
  }

//  @Override
//  public int compareTo(TutorialNode o)
//  {
//    return Double.compare(this.priority, o.priority);
//  }
//  
  
}