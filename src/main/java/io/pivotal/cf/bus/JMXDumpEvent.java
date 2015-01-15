package io.pivotal.cf.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@SuppressWarnings("serial")
public class JMXDumpEvent extends RemoteApplicationEvent {

	private final String objectName;
	private final String attribute;

	public JMXDumpEvent(Object source, String originService, String destinationService, String objectName, String attribute) {
		super(source, originService, destinationService);
		this.objectName = objectName;
		this.attribute = attribute;
	}

	/**
	 * For deserializer
	 */
	@SuppressWarnings("unused")
	private JMXDumpEvent() {
		this(new Object(), null, null, null, null);
	}

	public String getObjectName() {
		return objectName;
	}

	public String getAttribute() {
		return attribute;
	}

}
