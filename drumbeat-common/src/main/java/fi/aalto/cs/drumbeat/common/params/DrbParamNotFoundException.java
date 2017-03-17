package fi.aalto.cs.drumbeat.common.params;

import fi.aalto.cs.drumbeat.common.DrbNotFoundException;

public class DrbParamNotFoundException extends DrbNotFoundException {

	private static final long serialVersionUID = 1L;

	public DrbParamNotFoundException() {
	}

	public DrbParamNotFoundException(String arg0) {
		super(arg0);
	}

	public DrbParamNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public DrbParamNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DrbParamNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
