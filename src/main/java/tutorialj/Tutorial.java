
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
@Tutorial(showLink = true, linkTextPrefix = "See: ", linkTextSuffix = "")
public @interface Tutorial
{
  /**
   * @return Whether the source code should be displayed in this tutorial step
   */
  boolean showSource() default false;
  
  /**
   * @return Which .java file to recursively jump to, if any.
   */
  Class<?> nextStep() default NoJump.class;
  
  String startTutorial() default "";
  
  /**
   * @return A path prefix to be used if a link to the source code is generated
   */
  String linkPrefix() default "src/main/java/";
  
  /**
   * @return Whether a link to the source code should be shown in this tutorial step
   */
  boolean showLink() default false;
  
  /**
   * @return Text before the link
   */
  String linkTextPrefix() default "<sub>From:";
  
  /**
   * @return Text after the link
   */
  String linkTextSuffix() default "</sub>";
}
