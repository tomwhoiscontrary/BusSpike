package io.pivotal.cf.bus;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JMXDumpListener implements ApplicationListener<JMXDumpEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMXDumpListener.class);

	private MBeanServer mBeanServer;
	private ObjectMapper objectMapper;

	public JMXDumpListener(MBeanServer mBeanServer, ObjectMapper objectMapper) {
		this.mBeanServer = mBeanServer;
		this.objectMapper = objectMapper;
	}

	@Override
	public void onApplicationEvent(JMXDumpEvent event) {
		String objectName = event.getObjectName();
		String attribute = event.getAttribute();
		try {
			Object value = mBeanServer.getAttribute(ObjectName.getInstance(objectName), attribute);
			LOGGER.info(objectMapper.writeValueAsString(new JMXAttributeValue(objectName, attribute, String.valueOf(value))));
		} catch (JMException | JsonProcessingException e) {
			LOGGER.error("unable to get value of attribute {} on object {}", attribute, objectName, e);
		}
	}

}
