package fi.aalto.cs.drumbeat.common.params;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

public class SimpleTypeParams extends TreeMap<String, SimpleTypeParam<?>> {
	
	private static final long serialVersionUID = 1L;

	public void addParam(SimpleTypeParam<?> param) {
		super.put(param.getName(), param);
	}
	
	public void removeParam(SimpleTypeParam<?> param) {
		super.remove(param.getName(), param);
	}
	
	public SimpleTypeParam<?> getParam(String name) throws DrbParamNotFoundException {
		SimpleTypeParam<?> param = super.get(name);
		if (param == null) {
			throw new DrbParamNotFoundException(String.format("Unknown typed param: '%s'", name));
		}
		return param;
	}
	
	public <T> SimpleTypeParam<T> getParamEx(String name) throws DrbParamNotFoundException {
		@SuppressWarnings("unchecked")
		SimpleTypeParam<T> param = (SimpleTypeParam<T>)super.get(name);
		if (param == null) {
			throw new DrbParamNotFoundException(String.format("Unknown typed param: '%s'", name));
		}
		return param;
	}

	@SuppressWarnings("unchecked")
	public <T> SimpleTypeParam<T> getParamEx(String name, Class<T> typedParamClass, boolean autoCreate) throws DrbParamNotFoundException {
		SimpleTypeParam<T> param;
		try {
			param = (SimpleTypeParam<T>)getParam(name);
		} catch (DrbParamNotFoundException e) {			
			if (autoCreate) {
				try {
					Constructor<T> constructor = typedParamClass.getConstructor(String.class, String.class);
					param = (SimpleTypeParam<T>)constructor.newInstance(name, null);
					super.put(name, param);
				} catch (Exception e1) {
					throw new RuntimeException(e1);
				}
			} else {
				throw e;
			}			
		}
		return param;
	}

	public SimpleTypeParams clone() {
		SimpleTypeParams params = (SimpleTypeParams)super.clone();
		for (Map.Entry<String, SimpleTypeParam<?>> entry : params.entrySet()) {
			String name = entry.getKey();
			SimpleTypeParam<?> param = entry.getValue().clone();
			params.put(name, param);
		}
		return params;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParamValue(String name) throws DrbParamNotFoundException {
		return (T)getParam(name).getValue();
	}
	
	public <T> T getParamValue(String name, T defaultValue) {
		try {
			return getParamValue(name);
		} catch (DrbParamNotFoundException e) {
			return defaultValue;
		}
	}

	public <T> SimpleTypeParam<T> setParamValue(String name, T value) {
		SimpleTypeParam<T> param;
		try {
			param = this.<T>getParamEx(name);
			param.setValue(value);
		} catch (DrbParamNotFoundException e) {
			param = new SimpleTypeParam<T>() {{
			}

			@Override
			public void setStringValue(String s) {
				
			}};
		}
		return param;
	}
	
	public Collection<SimpleTypeParam<?>> getAllParams() {
		return super.values();
	}
	

}
