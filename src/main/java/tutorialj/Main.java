package tutorialj;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.commons.io.FileUtils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import spoon.Launcher;

public class Main
{
  public static boolean success = false;
  
  @Parameter(names = "--sourceFiles")
  private String sourceFiles = "src/main/java:src/test/java";
  
  @Parameter(names = "--verbose")
  private boolean verbose = false;
  
  
  public static void main(String [] args) throws Exception
  {
    Main main = new Main();
    new JCommander(main, args);
    main.run();
  }

  void run()
  {
    try
    {
      PrintStream saved = System.err;
      if (!verbose)
        System.setErr(new PrintStream(new OutputStream() {
          @Override
          public void write(int arg0) throws IOException
          {
            ;
          }}));
       
      String [] spoonArguments = {"--output-type", "nooutput", "-i", sourceFiles, "-p", "tutorialj.GenerateTutorials"};
      Launcher.main(spoonArguments);
      File spoonedFolder = new File("spooned");
      FileUtils.deleteDirectory(spoonedFolder);
      System.setErr(saved);
      if (!success)
        System.err.println("Problems encountered, use --verbose for details.");
    }
    catch (Throwable e)
    {
      if (verbose)
        throw new RuntimeException(e);
      else
        System.out.println(e.getMessage() + "\nUse --verbose for details.");

    }
  }

  public String getSourceFiles()
  {
    return sourceFiles;
  }

  public void setSourceFiles(String sourceFiles)
  {
    this.sourceFiles = sourceFiles;
  }
}
