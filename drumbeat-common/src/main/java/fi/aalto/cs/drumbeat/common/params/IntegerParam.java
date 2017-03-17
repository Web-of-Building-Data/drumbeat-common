package fi.aalto.cs.drumbeat.common.params;

import java.util.Collection;

public class IntegerParam extends SimpleTypeParam<Integer> {

	public IntegerParam() {
		this(null, null);
	}

	public IntegerParam(String name) {
		this(name, null);
	}	

	public IntegerParam(String name, String description) {
		super(name, description);
	}

	public IntegerParam(String name, String description, Integer value) {
		super(name, description, value);
	}

	public IntegerParam(String name, String description, Integer value,
			Collection<Integer> possibleValues,
			Collection<String> possibleValuesDescriptions, Integer defaultValue) {
		super(name, description, value, possibleValues,
				possibleValuesDescriptions, defaultValue);
	}

	@Override
	public void setStringValue(String s) {
		setValue(SimpleTypeConverter.toInteger(s));
	}

}
