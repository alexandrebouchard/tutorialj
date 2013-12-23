package tutorialj;

import java.util.Collections;
import java.util.List;

import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.declaration.CtElement;

import com.google.common.collect.Lists;


/**
 * 
 * @author Alexandre Bouchard (alexandre.bouchard@gmail.com)
 *
 */
public class GenerateTutorials extends
    AbstractAnnotationProcessor<Tutorial, CtElement> 
{ 
  private List<TutorialNode> nodes = Lists.newArrayList();
  
  @Override
  public void processingDone()
  {
    Collections.sort(nodes);
    for (TutorialNode node : nodes)
      System.out.println(node.toString());
  }
  
  public void process(Tutorial tutorialEntry, CtElement element) 
  {
    TutorialNode node = new TutorialNode(tutorialEntry, element);
    nodes.add(node);
  }


  
  // Note to self: to install Jekyll:
  // sudo gem update --system 
  // bis?!
  // sudo gem install jekyll
  // sudo gem install json
  
  
}