package tutorialj;

import spoon.Launcher;

public class Main
{
  public static void main(String [] args) throws Exception
  {
    if (args.length != 1)
    {
      System.out.println("1 argument: path to sources");
      System.exit(1);
    }
    
//    System.setErr(new PrintStream(new OutputStream() {
//      @Override public void write(int b) throws IOException {}
//    }));
    String [] spoonArguments = {"-i", args[0], "-p", "tutorialj.GenerateTutorials"};
    Launcher.main(spoonArguments);
  }
}
