package com.bsj.kyc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import com.google.common.base.Predicate;
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan
@Configuration
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0.O.Beta";
    private static final String LICENSE_TEXT = "LICENSE";
    private static final String title = "KYC REST API";
    private static final String description = "RESTful API for KYC";

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION)
                .contact(new Contact("Mohamad Basij", "https://github.com/mohamadbasij/KYC", "mohamadbiiij@gmail.com"))
                .build();
    }
    @Bean
    public Docket kycApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bsj.kyc.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
}