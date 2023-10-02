package edu.depaul.cdm.se452.fall2023group1;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppDocConfig {
    /**
     * Defines services documentation based Swagger
     * @see <a href="http://localhost:8080/swagger-ui/index.html">Swagger</a>
     * @param title
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${app.doc.title}") String title,
            @Value("${app.doc.version}") String version,
            @Value("${app.doc.description}") String description,
            @Value("${app.doc.terms}") String terms,
            @Value("${app.doc.license}") String license,
            @Value("${app.doc.url}") String url
    ) {
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description)
                .termsOfService(terms)
                .license(new License().name(license)
                        .url(url)));
    }
}
