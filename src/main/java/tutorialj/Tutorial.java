
package tutorialj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.SOURCE)
//@Target({ElementType.CONSTRUCTOR,ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Tutorial
{
  boolean showSource() default false;
//  public String next();
//  public Class<?> nextClass();
}
