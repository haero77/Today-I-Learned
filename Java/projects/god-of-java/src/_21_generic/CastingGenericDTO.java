package _21_generic;

import java.io.Serializable;

public class CastingGenericDTO<T> implements Serializable {

	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
