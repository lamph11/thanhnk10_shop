package shoe.store.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shoe.store.util.AppConstants;

@Configuration
@EnableJpaAuditing
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry
	        .addResourceHandler(AppConstants.IMAGE_RESOURCE_HANDLE + "/**")
	        .addResourceLocations("file:" + AppConstants.IMAGE_DIR + File.separator)
	        .setCachePeriod(0);
	}
}
