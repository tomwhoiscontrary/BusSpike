package io.pivotal.cf.bus;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RefreshScope
public class MainController {

	@Autowired
	private ApplicationInstanceInfo instanceInfo;

	@Value("${demo.colour}")
	private String colour;

	@Autowired
	private Environment environment;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("appId", instanceInfo.getAppId());
		modelAndView.addObject("instanceId", instanceInfo.getInstanceId());
		Map<String, Object> properties = instanceInfo.getProperties();
		modelAndView.addObject("instanceIndex", properties.get("instance_index"));
		modelAndView.addObject("properties", properties);
		modelAndView.addObject("colour", colour);
		modelAndView.addObject("flavour", environment.getProperty("demo.flavour"));
		return modelAndView;
	}

}
