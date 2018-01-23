package pro.abacus.webRestProject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;
import pro.abacus.webRestProject.Controllers.HomeController;

@Slf4j
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		log.info("in mvc configuration");
		registry.addViewController("/").setViewName("home");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		log.info("configure servlet handling");
		configurer.enable();
	}

	@Bean
	InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/templates");
		return viewResolver;
	}

}
