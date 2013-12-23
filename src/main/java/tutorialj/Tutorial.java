
package tutorialj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
/**
 * API
 * ---
 */
@Tutorial(order=3,showSource=true)
public @interface Tutorial
{
  boolean showSource() default false;
  double order() default 0.0;
}
