package tutorialj;

import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import com.google.common.base.Joiner;



public class TutorialNode 
{
  private final Tutorial annotation;
  private final CtMethod<?> element;
  
  public TutorialNode(Tutorial annotation, CtElement element)
  {
    this.annotation = annotation;
    this.element = (CtMethod<?>) element;
  }
  
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
    
    StringBuilder sourceBlock = new StringBuilder();
    if (annotation.showSignature())
      sourceBlock.append(fullSignature(element) + "\n");
    if (annotation.showSource())
    {
      if (annotation.showSignature()) sourceBlock.append("{\n");
      sourceBlock.append(code(element).replaceFirst("\\n\\s+$", "\n"));
      if (annotation.showSignature()) sourceBlock.append("}\n");
    }
    if (sourceBlock.length() > 0)
    {
      result.append("```java\n");
      result.append(removeManyFirstSpacesOfEachLine(sourceBlock.toString())); // + "\n"); already a trailing new line in removeManyFirstSpacesOfEachLine()
      result.append("```\n");
    }
    
    if (annotation.showLink())
    {
      String className = getEnclosingTypeName();
      result.append(annotation.linkTextPrefix() 
          + "[" + className + "](" + annotation.linkPrefix() + "/" + className.replace('.', '/') 
          + ".java)" + annotation.linkTextSuffix() + "\n");
    }
    return result.toString();
  }
  
  public int getLineNumber()
  {
    return element.getPosition().getLine() - 1;
  }
  
  public String getEnclosingTypeName()
  {
    CtElement element = this.element.getPosition().getCompilationUnit().getMainType();
    return removeFirstPartOfSignature(element.getSignature());
  }
  
  private static String removeFirstPartOfSignature(String s)
  {
    return s.replaceFirst("(class|interface|[@]interface)\\s+", "");
  }

  private static String removeFirstSpaceOfEachLine(String comments)
  {
    StringBuilder result = new StringBuilder();
    for (String line : comments.split("\\n")) //Splitter.on("\n").split(comments))
    {
      if (!line.isEmpty() && line.charAt(0) == ' ')
        line = line.substring(1);
      result.append(line + "\n");
    }
    return result.toString();
  }
  
  private static String removeManyFirstSpacesOfEachLine(String contents)
  {
    int min = Integer.MAX_VALUE;
    for (String line : contents.split("\\n")) //Splitter.on("\n").split(contents))
      if (!line.matches("^\\s*$"))
      {
        int current = 0;
        inner:for (char aChar : line.toCharArray())
          if (aChar == ' ')
            current++;
          else
            break inner;
        if (current < min)
          min = current;
      }
    StringBuilder result = new StringBuilder();
    for (String line : contents.split("\\n")) //Splitter.on("\n").split(contents))
      result.append((line.matches("^\\s*$") ? "" : line.substring(min)) + "\n");
    return result.toString();
  }
  
  private static String fullSignature(CtMethod<?> m)
  {
    return 
      (m.getModifiers().isEmpty() ? "" : Joiner.on(" ").join(m.getModifiers()) + " ") + 
      m.getSignature() + 
      (m.getThrownTypes().isEmpty() ? "" : " throws " + Joiner.on(", ").join(m.getThrownTypes()));
  }

  private static String code(CtMethod<?> m) 
  {
    SourcePosition p =  m.getBody().getPosition();
    return m.getPosition().getCompilationUnit().getOriginalSourceCode().substring(p.getSourceStart(), p.getSourceEnd()).replaceFirst("^\\s*\\n", "");
  }

  
}