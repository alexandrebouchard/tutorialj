package tutorialj;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.media.jai.IntegerSequence;

import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.declaration.CtElement;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;

import static com.google.common.base.Optional.*;


/**
 * 
 * @author Alexandre Bouchard (alexandre.bouchard@gmail.com)
 *
 */
public class GenerateTutorials extends
    AbstractAnnotationProcessor<Tutorial, CtElement> 
{ 
  private List<TutorialNode> tutorialNodesInOrder = Lists.newArrayList();
  
  private Map<String, LinkedList<TutorialNode>> nodesPerTypeName = Maps.newHashMap();
  private String startType = null;
  private String outputName;
  
  @Override
  public void processingDone()
  {
    // sort each queue by line number
    for (LinkedList<TutorialNode> list : nodesPerTypeName.values())
      Collections.sort(list, new Comparator<TutorialNode>() {
        @Override public int compare(TutorialNode t0, TutorialNode t1)
        {
          return Integer.valueOf(t0.getLineNumber()).compareTo(t1.getLineNumber());
        }
      });
    
    traverseOrder(startType);
    
    if (!isStartDefined())
      throw new RuntimeException("One node should have a startTutorial(String name) attribute, " +
      		"where name specifies the name of the output file.");
    
    File outputFile = getAndCheckOutputFile();
    
    StringBuilder out = new StringBuilder();
    
    out.append(commentLine + "\n");
    
    for (TutorialNode node : tutorialNodesInOrder)
      out.append(node.toString() + "\n");
    
    try
    {
      Files.write(out, outputFile, Charsets.UTF_8);
      Main.success = true;
    } catch (IOException e)
    {
      throw new RuntimeException();
    }
  }
  
  private File getAndCheckOutputFile()
  {
    File candidate = new File(outputName);
    if (!candidate.exists())
      return candidate;
    try
    {
      String firstLine = Files.readFirstLine(candidate, Charsets.UTF_8);
      if (!commentLine.equals(firstLine))
        throw new RuntimeException("Looks like the file " + candidate.getAbsolutePath() + 
            " was not generated by tutorialj (it does not start with \"" + commentLine + "\", " +
            		"so we will not overwrite it.");
      return candidate;
    } catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  private static final String commentLine = "<!-- File generated by tutorialj -->";

  private boolean isStartDefined()
  {
    return startType != null;
  }

  /**
   * Nodes are traversed recursively as follows, where the method is
   * first called with the type declaring the ``tutorialStart`` argument
   */
  @Tutorial(showSource=true, showLink = true)
  private void traverseOrder(String currentType)
  {
    // consume node until a jump is found
    LinkedList<TutorialNode> currentQueue = 
      fromNullable(nodesPerTypeName.get(currentType)).or(Lists.<TutorialNode>newLinkedList());
    while (!currentQueue.isEmpty())
    {
      TutorialNode current = currentQueue.pollFirst();
      tutorialNodesInOrder.add(current);
      if (current.hasJump())
        traverseOrder(current.getJumpTarget());
    }
  }

  public void process(Tutorial tutorialEntry, CtElement element) 
  {
    TutorialNode node = new TutorialNode(tutorialEntry, element);
    String enclosingType = node.getEnclosingTypeName();
    LinkedList<TutorialNode> currentQueue = nodesPerTypeName.get(enclosingType);
    if (currentQueue == null)
      nodesPerTypeName.put(enclosingType, currentQueue = Lists.newLinkedList());
    currentQueue.add(node);
    if (node.isTutorialStart())
    {
      if (startType != null)
        System.err.println("WARNING: two tutorial starts defined, " +
        		"but multiple tutorials currently not supported. Resetting " +
        		"the tutorial start for now; some of your tutorial nodes might" +
        		"not show up.");
      startType = node.getEnclosingTypeName();
      outputName = tutorialEntry.startTutorial();
    }
  }
  // Note to self: to install Jekyll:
  // sudo gem update --system 
  // bis?!
  // sudo gem install jekyll
  // sudo gem install json
}