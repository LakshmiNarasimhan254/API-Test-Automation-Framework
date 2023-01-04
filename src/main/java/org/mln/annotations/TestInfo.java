package org.mln.annotations;

import org.mln.enums.Categories;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
// An annotation.
public @interface TestInfo {

     String[] author() default "";
     Categories[] categories() default Categories.SMOKE;


}
