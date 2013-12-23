
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
  boolean showSource() default false;
  double order() default 0.0; // TODO: for methods/fields/constructors, make it inherit order in file by default
}
