package tutorialj;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;



public class Test
{
  public static void main(String[]args)
  {
    Object tst = "asdf";
    Class<?> c1 = tst.getClass();
    System.out.println(c1);
    
    Object tst2 = "asdfasdf";
    Class<?> c2 = tst2.getClass();
    
    System.out.println(c1.equals(c2));
    
    Queue<String> q = new LinkedList<String>();
    
    Class<?> c10 = Object.class;
    Class<?> c11 = Runnable.class;
    
    System.out.println(c11);
    
    Map<String,List<String>> asdf = Maps.newHashMap();
    
//    Optional.from(asdf.get())
  }
}
