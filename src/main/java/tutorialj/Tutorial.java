
package tutorialj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Tutorial
{
  /**
   * @return Whether the code of this method/function body should be displayed
   */
  boolean showSource() default true;
  
  /**
   * @return Whether the signature of this method/function should be displayed
   */
  boolean showSignature() default false;
  
  /**
   * @return Which .java file to recursively jump to, if any.
   */
  Class<?> nextStep() default NoJump.class;
  
  /**
   * @return The identifier of the tutorial, currently also used as the name of the file to output to 
   */
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
