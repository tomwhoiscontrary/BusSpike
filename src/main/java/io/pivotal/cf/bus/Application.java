package io.pivotal.cf.bus;

import java.util.Collections;

import javax.management.MBeanServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.app.BasicApplicationInstanceInfo;
import org.springframework.cloud.bus.endpoint.BusEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	@Profile("!cloud")
	public ApplicationInstanceInfo applicationInstanceInfo() {
		return new BasicApplicationInstanceInfo("local", "BusSpike", Collections.emptyMap());
	}

	@Bean
	public JMXDumpListener jmxDumpListener(MBeanServer mBeanServer) {
		return new JMXDumpListener(mBeanServer, new ObjectMapper());
	}

	@Bean
	public JMXDumpBusEndpoint jmxDumpBusEndpoint(ApplicationContext context, BusEndpoint busEndpoint) {
		return new JMXDumpBusEndpoint(context, context.getId(), busEndpoint);
	}

}
