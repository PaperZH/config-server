package com.ucar.qtc.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: cong.zhou01
 * @description: SpringMVC拦截器配置
 * @date: 2018/8/7 10:20
 */
@Configuration
@EnableSwagger2
public class Swigger2Config extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name("Authorization")
                .description("JWT_TOKEN")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ucar.qtc.home.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API-HOME服务接口文档")
                .description("API-HOME服务接口文档")
                .termsOfServiceUrl("http://localhost:8001")
                .contact(new Contact("cong.zhou01",
                        "www.ucarinc.com",
                        "cong.zhou01@ucarinc.com")).version("2.0").build();
    }
}
