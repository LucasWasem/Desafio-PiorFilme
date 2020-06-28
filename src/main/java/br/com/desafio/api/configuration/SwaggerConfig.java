package br.com.desafio.api.configuration;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author lucas
 * @since 27/06/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio.api.resources"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Golden Raspberry Awards",
                "Pior Filme",
                "1.0",
                null,
                new Contact("Lucas", "https://www.linkedin.com/in/lucas-wasem-9638a114a/", "lucaswasemsci@gmail.com"),
                null,
                null,
                Collections.emptyList());
    }
}
