package config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "main")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

}
