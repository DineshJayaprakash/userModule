package UserModule.UserModule.Aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * TrackExecutionTime
 * 
 * @created By Dinesh J
 * @CreatedDate : 09/12
 * @description used to track the execution time of that methodwhich is annotated with TrackExecutionTime annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackExecutionTime {

}
