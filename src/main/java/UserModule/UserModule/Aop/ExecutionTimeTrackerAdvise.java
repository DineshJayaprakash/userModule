package UserModule.UserModule.Aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * ExecutionTimeTrackerAdvise
 * 
 * @created By Dinesh J
 * @CreatedDate : 09/12
 * @description used to log all the methods which is mapped with TrackExecutionTime annotation
 */
@Aspect
@Component
public class ExecutionTimeTrackerAdvise {

	/**
	 * Logger bean
	 */
	Logger logger = org.slf4j.LoggerFactory.getLogger(ExecutionTimeTrackerAdvise.class);
	
	/**
	 * 
	 * @param pjp
	 * @return Object
	 * @throws Throwable
	 * @description used display the execution time of all function which is mapped with TrackExecutionTime annotation
	 */
	@Around(value = "@annotation(UserModule.UserModule.Aop.TrackExecutionTime)")
	public Object trackTimeExecution(ProceedingJoinPoint pjp) throws Throwable {
		long startTime =System.currentTimeMillis();
		Object myObj = pjp.proceed();
		long endTime =System.currentTimeMillis();
		logger.info("Method name"+" "+ pjp.getSignature()+" "+ "time taken to execute"+" "+ (endTime-startTime)+" ms");
		return myObj;
		
	}
}
