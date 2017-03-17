package fi.aalto.cs.drumbeat.common.meta;

public class MetaClassUtils {

	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * @param callingMethodShift depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	public static String getCallingMethodName(final int callingMethodShift)
	{
	  StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	  return ste[callingMethodShift + 2].getMethodName();
	}
	
	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * @param callingMethodShift depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	public static String getCallingClassName(final int callingMethodShift)
	{
	  StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	  return ste[callingMethodShift + 2].getClassName();
	}
	
	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * @param callingMethodShift depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	public static String getFullCallingMethodName(final int callingMethodShift)
	{
	  StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	  return String.format("%s/%s", ste[callingMethodShift + 2].getClassName(), ste[callingMethodShift + 2].getMethodName());
	}
	
	

}
