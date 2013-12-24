
package tutorialj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * API
 * ---
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Tutorial(order=3,showSource=true)
public @interface Tutorial
{
  /**
   * @return Whether the source code should be shown in this tutorial step
   */
  boolean showSource() default false;
  
  /**
   * @return The index in the order of presentation (typically an integer,
   * but double can be useful to insert intermediate steps)
   */
  double order() default 0.0; // TODO: for methods/fields/constructors, make it inherit order in file by default
  
  /**
   * @return A path prefix to be used if a link to the source code is generated
   */
  String linkPrefix() default "src/main/java/";
  
  /**
   * @return Whether a link to the source code should be shown in this tutorial step
   */
  boolean showLink() default false;
}
