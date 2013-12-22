import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import tutorialj.Bound;




public class Bound2Processor extends
    AbstractAnnotationProcessor<Bound, CtParameter<?>> {
  public void process(Bound annotation, CtParameter<?> element) {
    // we declare a new snippet of code to be inserted
    CtCodeSnippetStatement snippet = getFactory().Core().createCodeSnippetStatement();

    // this snippet contains an if check
    snippet.setValue("if(" + element.getSimpleName() + " < "
        + annotation.min()
        + ") throw new RuntimeException(\"[Spoon check] Bound violation\");");

    // we insert the snippet at the beginning of the method boby
    element.getParent(CtMethod.class).getBody().insertBegin(snippet);
  }
}