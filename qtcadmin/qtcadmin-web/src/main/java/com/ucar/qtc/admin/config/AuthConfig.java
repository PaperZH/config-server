package com.ucar.qtc.admin.config;

import com.ucar.qtc.common.constants.CommonConstants;
import com.ucar.qtc.common.intercepter.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.*;
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
public class AuthConfig extends WebMvcConfigurationSupport {

    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/homeLogin**");
        addInterceptor.excludePathPatterns("/test**");
        addInterceptor.excludePathPatterns("/pages/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name(CommonConstants.CONTEXT_TOKEN)
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
                .apis(RequestHandlerSelectors.basePackage("com.ucar.qtc.admin.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API-ADMIN服务接口文档")
                .description("API-ADMIN服务接口文档").termsOfServiceUrl("http://localhost:8001")
                .contact(new Contact("cong.zhou01",
                        "www.ucarinc.com",
                        "cong.zhou01@ucarinc.com")).version("2.0").build();
    }
}
