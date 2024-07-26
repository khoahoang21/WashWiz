package group5.webapp.FinalProjectOOP.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

//    private String appVersion = ;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("API Documentation").version("v1.1.1"));
    }

}