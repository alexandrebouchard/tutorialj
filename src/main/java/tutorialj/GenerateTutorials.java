package tutorialj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;


/**
 * 
 * This one appear though
 * 
 * @customTag test thist is a long one
 *  is it going to go that far?
 * @author Alexandre Bouchard (alexandre.bouchard@gmail.com)
 *
 */
public class GenerateTutorials extends
    AbstractAnnotationProcessor<Tutorial, CtElement> 
{ 
  @Override
  public void processingDone()
  {
    System.out.println("All done!");
  }
  
  
  public void process(Tutorial tutorialNode, CtElement element) 
  {
//    System.out.println("Processing: " + element);
    String comments = element.getDocComment();
    if (comments != null)
      System.out.println("Comments:\n" + element.getDocComment());
    if (tutorialNode.showSource())
      System.out.println("Source:\n" + element);
    System.out.println("Signature: " + element.getSignature());
    System.out.println();
    
//    for (Method m : element.getClass().getDeclaredMethods())
//      if (m.toString().contains("main"))
//        System.out.println("!!!!!!!!!!");
//    
    
    
//    GenerateTutorials.class.
//    // we declare a new snippet of code to be inserted
////    CtCodeSnippetStatement snippet = getFactory().Core().createCodeSnippetStatement();
//
//    // this snippet contains an if check
////    snippet.setValue("if(" + element.getSimpleName() + " < "
////        + annotation.min()
////        + ") throw new RuntimeException(\"[Spoon check] Bound violation\");");
//    
//    System.out.println("Processing: " + element);
//    System.out.println(element.getParent().getDocComment());
    

    // we insert the snippet at the beginning of the method boby
//    element.getParent(CtMethod.class).getBody().insertBegin(snippet);
  }


  
  // to install Jekyll:
  // sudo gem update --system 
  // bis?!
  // sudo gem install jekyll
  // sudo gem install json
  
  
}

//java -cp bin/:$CLASSPATH spoon.Launcher -i src/main/java/ -p tutorialj.GenerateTutorials

//CtElement
//CtAbstractInvocation<T>, CtAnnotation<A>, CtAnnotationType<T>, CtAnonymousExecutable, CtArrayAccess<T,E>, CtAssert<T>, CtAssignment<T,A>, CtBinaryOperator<T>, CtBlock<R>, CtBreak, CtCase<S>, CtCatch, CtCFlowBreak, CtClass<T>, CtCodeElement, CtCodeSnippetExpression<T>, CtCodeSnippetStatement, CtConditional<T>, CtConstructor<T>, CtContinue, CtDo, CtEnum<T>, CtExecutable<R>, CtExpression<T>, CtField<T>, CtFieldAccess<T>, CtFor, CtForEach, CtGenericElement, CtIf, CtInterface<T>, CtInvocation<T>, CtLiteral<T>, CtLocalVariable<T>, CtLoop, CtMethod<T>, CtModifiable, CtNamedElement, CtNewArray<T>, CtNewClass<T>, CtOperatorAssignment<T,A>, CtPackage, CtParameter<T>, CtReturn<R>, CtSimpleType<T>, CtStatement, CtStatementList<R>, CtSwitch<S>, CtSynchronized, CtTargetedExpression<T,E>, CtThrow, CtTry, CtType<T>, CtTypedElement<T>, CtTypeParameter, CtUnaryOperator<T>, CtVariable<T>, CtVariableAccess<T>, CtWhile

//{
  
  
  
////  javadoc -docletpath build/libs/tutorialj-0.0.jar -doclet tutorialj.GenerateTutorials -sourcepath src/main/java/ -subpackages tutorialj 
//  
//  public static boolean start(RootDoc root) {
//    
//    for (ClassDoc classDoc : root.classes())
//    {
//      for (Tag tag : classDoc.tags())
//        System.out.println(tag);
////      classDoc.
//    }
////    ClassDoc[] classes = root.classes();
////    for (int i = 0; i < classes.length; ++i) {
////        System.out.println(classes[i]);
////    }
//    return true;
//}
//}
