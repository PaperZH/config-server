package com.ucar.qtc.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cong.zhou01
 * @name DocumentationConfig
 * @description zuul读取eureka注册的所有服务
 * @date 2018-09-12 17:42
 */
@Component
@Primary
@EnableSwagger2
class DocumentationConfig implements SwaggerResourcesProvider {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String zuulName;

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        for (String str : discoveryClient.getServices()) {
            List<ServiceInstance> listInstance = discoveryClient.getInstances(str);
            for (ServiceInstance instance : listInstance) {
                String serviceId = instance.getServiceId();
                String host = instance.getHost();
                if (!str.equalsIgnoreCase(zuulName) && str.equalsIgnoreCase(serviceId)) {
                    resources.add(swaggerResource(host+"/"+str, "/"+ str +"/v2/api-docs", "2.0"));
                }
            }
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
