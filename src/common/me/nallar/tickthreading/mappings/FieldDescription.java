package me.nallar.tickthreading.mappings;

import java.lang.reflect.Field;

import javassist.CtField;

public class FieldDescription {
	public final String className;
	public final String name;
	private Integer cachedHashCode = null;

	public FieldDescription(String className, String name) {
		this.className = className;
		this.name = name;
	}

	public FieldDescription(String MCPName) {
		this(MCPName.substring(0, MCPName.lastIndexOf('/')).replace('/', '.'), MCPName.substring(MCPName.lastIndexOf('/') + 1));
	}

	public FieldDescription(Field field) {
		this(field.getDeclaringClass().getName(), field.getName());
	}

	public FieldDescription(CtField ctField) {
		this(ctField.getDeclaringClass().getName(), ctField.getName());
	}

	@Override
	public int hashCode() {
		if (cachedHashCode != null) {
			return cachedHashCode;
		}
		int hashCode = className.hashCode();
		hashCode = 31 * hashCode + name.hashCode();
		return (cachedHashCode = hashCode);
	}

	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof FieldDescription && other.hashCode() == this.hashCode());
	}

	@Override
	public String toString() {
		return className + "." + name;
	}
}
