tutorialj
=========

Annotation-based utility to create tutorials from java code


```java
@Retention(RetentionPolicy.SOURCE)
//@Target({ElementType.CONSTRUCTOR,ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Tutorial
{
  boolean showSource() default false;
  
//  public String next();
//  public Class<?> nextClass();
}
```