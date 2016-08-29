package fi.aalto.cs.drumbeat.common;

/**
 * A runtime exception indicating a resource was not found.
 * 
 * @author Nam Vu
 *
 */
public class DrbNotFoundException extends DrbException {

	private static final long serialVersionUID = 1L;

	public DrbNotFoundException() {
	}

	public DrbNotFoundException(String arg0) {
		super(arg0);
	}

	public DrbNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public DrbNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DrbNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
