package fi.aalto.cs.drumbeat.common;

/**
 * Super class for all exceptions in the DRUMBEAT software
 * 
 * @author Nam Vu
 *
 */
public class DrbException extends Exception {

	private static final long serialVersionUID = 1L;

	public DrbException() {
	}

	public DrbException(String arg0) {
		super(arg0);
	}

	public DrbException(Throwable arg0) {
		super(arg0);
	}

	public DrbException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DrbException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
