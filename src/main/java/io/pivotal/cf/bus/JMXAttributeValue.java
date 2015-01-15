package io.pivotal.cf.bus;

public class JMXAttributeValue {

	private final String objectName;
	private final String attribute;
	private final String value;

	public JMXAttributeValue(String objectName, String attribute, String value) {
		this.objectName = objectName;
		this.attribute = attribute;
		this.value = value;
	}

	public String getObjectName() {
		return objectName;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getValue() {
		return value;
	}

}