package io.pivotal.cf.bus;

import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.cloud.bus.endpoint.BusEndpoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class JMXDumpBusEndpoint extends AbstractBusEndpoint {

	public JMXDumpBusEndpoint(ApplicationEventPublisher context, String id, BusEndpoint delegate) {
		super(context, id, delegate);
	}

	@RequestMapping(value = "jmx/dump", method = RequestMethod.POST)
	@ResponseBody
	public void dump(@RequestParam(value = "destination", required = false) String destination, String objectName, String attribute) {
		publish(new JMXDumpEvent(this, getInstanceId(), destination, objectName, attribute));
	}

}
