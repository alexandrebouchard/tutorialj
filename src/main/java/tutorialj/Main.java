package tutorialj;

/**
 * A comment
 */
public class Main
{
  public static void main(String [] args)
  {
  }
  
  public static void badTest() {}
  
  /**
   * Oui!
   * @param test
   */
  @Tutorial(showSource=true) public  static void test(int test)
  {
    System.out.println("Test:" + test);
  }
}
